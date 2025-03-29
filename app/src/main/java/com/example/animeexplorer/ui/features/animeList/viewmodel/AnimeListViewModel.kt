package com.example.animeexplorer.ui.features.animeList.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.cachedIn
import com.example.animeexplorer.domain.entities.Anime
import com.example.animeexplorer.domain.repository.AnimeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged

class AnimeListViewModel(
    private val repository: AnimeRepository
) : ViewModel() {

    private val _animes: MutableStateFlow<PagingData<Anime>> =
        MutableStateFlow(value = PagingData.empty())
    val animes: StateFlow<PagingData<Anime>> get() = _animes

    suspend fun getAnimeListPaginated() {
        pager
            .distinctUntilChanged()
            .collect {
                _animes.value = it
            }
    }

    private val pager = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = false,
            prefetchDistance = 1
        ),
        pagingSourceFactory = {
            AnimePagingSource()
        }
    ).flow.cachedIn(viewModelScope)

    inner class AnimePagingSource : PagingSource<Int, Anime>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Anime> {
            val page = params.key ?: 1
            return try {
                val response = repository.getAnimeList(page, PAGE_SIZE)
                LoadResult.Page(
                    data = response.data ?: listOf(),
                    prevKey = null,
                    nextKey = if (response.data.isNullOrEmpty()) null else page + 1
                )
            } catch (exception: Exception) {
                LoadResult.Error(exception)
            }
        }

        override fun getRefreshKey(state: PagingState<Int, Anime>): Int? {
            return state.anchorPosition
        }
    }

    companion object {
        const val PAGE_SIZE = 10
    }

}