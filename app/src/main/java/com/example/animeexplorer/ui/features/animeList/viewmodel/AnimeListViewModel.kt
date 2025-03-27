package com.example.animeexplorer.ui.features.animeList.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeexplorer.domain.entities.Anime
import com.example.animeexplorer.domain.repository.AnimeRepository
import com.example.animeexplorer.domain.util.Response
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AnimeListViewModel(
    private val repository: AnimeRepository
) : ViewModel() {

    private val _animeListUiState = Channel<AnimeListUiState>()
    val animeListUiState get() = _animeListUiState.receiveAsFlow()

    fun getAnimeList() {
        viewModelScope.launch {
            _animeListUiState.send(AnimeListUiState.Loading)
            repository.getAnimeList().run {
                when (this) {
                    is Response.Error -> {
                        _animeListUiState.send(AnimeListUiState.Error(this.error))
                    }

                    is Response.Success -> {
                        _animeListUiState.send(
                            if (this.data.isNullOrEmpty()) {
                                AnimeListUiState.EmptyList
                            } else {
                                AnimeListUiState.AnimeList(this.data)
                            }
                        )
                    }
                }
            }
        }
    }

    sealed class AnimeListUiState {
        data object Loading : AnimeListUiState()
        data class Error(val error: String) : AnimeListUiState()
        data object EmptyList : AnimeListUiState()
        data class AnimeList(val animeList: List<Anime>) : AnimeListUiState()
    }
}