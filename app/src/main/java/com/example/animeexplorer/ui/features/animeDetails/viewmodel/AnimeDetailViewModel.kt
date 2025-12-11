package com.example.animeexplorer.ui.features.animeDetails.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animeexplorer.domain.entities.AnimeDetails
import com.example.animeexplorer.domain.repository.AnimeRepository
import com.example.animeexplorer.domain.repository.UserRepository
import com.example.animeexplorer.domain.util.Response
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AnimeDetailViewModel(
    savedStateHandle: SavedStateHandle,
    private val repository: AnimeRepository,
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _animeDetailsUiState = MutableStateFlow(AnimeDetailsUiState())
    val animeDetailsUiState get() = _animeDetailsUiState as StateFlow<AnimeDetailsUiState>

    private fun updateState(
        isLoading: Boolean? = null,
        error: String? = null,
        animeDetails: AnimeDetails? = null,
        animeImages: List<String>? = null
    ) {
        viewModelScope.launch {
            val state = animeDetailsUiState.value
            val details = animeDetails ?: state.animeDetails
            val images =
                (details?.posterUrl.orEmpty() + (animeImages ?: emptyList())).toMutableSet()

            _animeDetailsUiState.emit(
                state.copy(
                    isLoading = isLoading ?: false,
                    error = error ?: state.error,
                    animeDetails = details?.copy(posterUrl = images),
                )
            )
        }
    }

    var isCompleted: Flow<Boolean>? = null
    var isBookmarked: Flow<Boolean>? = null

    init {
        savedStateHandle.get<Int>("animeId")?.let {
            getAnimeDetails(it)
            getAnimeImages(it)
            isCompleted = userRepository.getIsCompleted(it)
            isBookmarked = userRepository.getIsBookmarked(it)
        } ?: {
            updateState(error = "Anime Not Found")
        }
    }

    fun updateIsCompleted(isCompleted: Boolean?, animeId: String) {
        viewModelScope.launch {
            if (isCompleted == true) {
                userRepository.removeCompleted(animeId)
            } else {
                userRepository.upsertCompleted(animeId)
            }
        }
    }

    private fun getAnimeDetails(animeId: Int) {
        viewModelScope.launch {
            updateState(isLoading = true)
            repository.getAnimeDetails(animeId).run {
                when (this) {
                    is Response.Error -> {
                        updateState(error = this.error)
                    }

                    is Response.Success -> {
                        updateState(
                            animeDetails = this.value,
                        )
                    }
                }
            }
        }
    }

    private fun getAnimeImages(animeId: Int) {
        viewModelScope.launch {
            repository.getAnimeImageList(animeId).run {
                when (this) {
                    is Response.Error -> {
                        updateState(error = this.error)
                    }

                    is Response.Success -> {
                        updateState(animeImages = this.value)
                    }
                }
            }
        }
    }

    fun updateIsBookmarked(isBookmarked: Boolean?, animeId: String) {
        viewModelScope.launch {
            if (isBookmarked == true) {
                userRepository.removeBookmarked(animeId)
            } else {
                userRepository.upsertBookmarked(animeId)
            }
        }
    }

    data class AnimeDetailsUiState(
        var isLoading: Boolean = true,
        var error: String? = null,
        var animeDetails: AnimeDetails? = null,
    )

}