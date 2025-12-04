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
import com.example.animeexplorer.domain.util.Response
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class AnimeListViewModel(
    private val repository: AnimeRepository
) : ViewModel() {

    private val _animes: MutableStateFlow<PagingData<Anime>> =
        MutableStateFlow(value = PagingData.empty())
    val animes: StateFlow<PagingData<Anime>> get() = _animes

    suspend fun getAnimeListPaginated() {
        getAnimePager { pageNumber ->
            repository.getAnimeList(pageNumber, PAGE_SIZE)
        }
            .distinctUntilChanged()
            .collect {
                _animes.value = it
            }
    }

    private var searchJob: Job? = null
    fun searchByText(text: String) {
        _animes.value = PagingData.empty()
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(300)
            getAnimePager { pageNumber ->
                repository.getAnimeListByText(pageNumber, PAGE_SIZE, text)
            }
                .distinctUntilChanged()
                .collect {
                    _animes.value = it
                }
        }
    }

    private fun getAnimePager(
        getAnimeList: suspend (Int) -> Response<List<Anime>>
    ): Flow<PagingData<Anime>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = false,
                prefetchDistance = 1
            ),
            pagingSourceFactory = {
                AnimePagingSource { pageNumber ->
                    getAnimeList(pageNumber)
                }
            }
        ).flow.cachedIn(viewModelScope)
    }

    class AnimePagingSource(
        val getAnimeList: suspend (Int) -> Response<List<Anime>>,
    ) : PagingSource<Int, Anime>() {
        override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Anime> {
            val page = params.key ?: 1
            return try {
                val response = getAnimeList(page)
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