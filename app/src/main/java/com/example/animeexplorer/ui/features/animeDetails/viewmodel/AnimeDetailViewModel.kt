package com.example.animeexplorer.ui.features.animeDetails.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeexplorer.domain.entities.AnimeDetails
import com.example.animeexplorer.domain.repository.AnimeRepository
import com.example.animeexplorer.domain.util.Response
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AnimeDetailViewModel(
    private val repository: AnimeRepository
) : ViewModel() {

    private val _animeDetailsUiState = Channel<AnimeDetailsUiState>()
    val animeDetailsUiState get() = _animeDetailsUiState.receiveAsFlow()

    fun getAnimeDetails(animeId: Int) {
        viewModelScope.launch {
            _animeDetailsUiState.send(
                AnimeDetailsUiState.Loading
            )
            repository.getAnimeDetails(animeId).run {
                when (this) {
                    is Response.Error -> {
                        _animeDetailsUiState.send(
                            AnimeDetailsUiState.Error(this.error)
                        )
                    }

                    is Response.Success -> {
                        this.data?.let {
                            _animeDetailsUiState.send(
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