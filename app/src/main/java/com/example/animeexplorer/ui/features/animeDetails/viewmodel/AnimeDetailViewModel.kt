package com.example.animeexplorer.ui.features.animeDetails.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeexplorer.domain.entities.AnimeDetails
import com.example.animeexplorer.domain.repository.AnimeRepository
import com.example.animeexplorer.domain.util.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AnimeDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: AnimeRepository
) : ViewModel() {

    private val _animeDetailsUiState =
        MutableStateFlow<AnimeDetailsUiState>(AnimeDetailsUiState.Loading)
    val animeDetailsUiState get() = _animeDetailsUiState as StateFlow<AnimeDetailsUiState>

    init {
        savedStateHandle.get<Int>("animeId")?.let {
            getAnimeDetails(it)
        } ?: {
            viewModelScope.launch {
                _animeDetailsUiState.emit(
                    AnimeDetailsUiState.Error("Anime Not Found")
                )
            }
        }
    }

    private fun getAnimeDetails(animeId: Int) {
        viewModelScope.launch {
            _animeDetailsUiState.emit(
                AnimeDetailsUiState.Loading
            )
            repository.getAnimeDetails(animeId).run {
                when (this) {
                    is Response.Error -> {
                        _animeDetailsUiState.emit(
                            AnimeDetailsUiState.Error(this.error)
                        )
                    }

                    is Response.Success -> {
                        this.data?.let {
                            _animeDetailsUiState.emit(
                                AnimeDetailsUiState.AnimeDetailsState(it)
                            )
                        }
                    }
                }
            }
        }
    }


    sealed class AnimeDetailsUiState {
        data object Loading : AnimeDetailsUiState()
        data class Error(val error: String) : AnimeDetailsUiState()
        data class AnimeDetailsState(val details: AnimeDetails) : AnimeDetailsUiState()
    }

}