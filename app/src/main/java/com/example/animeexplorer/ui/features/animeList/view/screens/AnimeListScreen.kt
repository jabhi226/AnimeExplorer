package com.example.animeexplorer.ui.features.animeList.view.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.animeexplorer.ui.features.animeList.view.component.AnimeListItem
import com.example.animeexplorer.ui.features.animeList.viewmodel.AnimeListViewModel
import com.example.animeexplorer.ui.features.animeList.viewmodel.AnimeListViewModel.*
import com.example.animeexplorer.ui.features.core.screen.ErrorScreen
import com.example.animeexplorer.ui.features.core.screen.LoadingScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Composable
fun AnimeListScreen(
    modifier: Modifier = Modifier,
    onAnimeClicked: (Int) -> Unit
) {

    val viewModel = koinViewModel<AnimeListViewModel>()
    val uiState by viewModel.animeListUiState.collectAsState(initial = AnimeListUiState.Loading)

    LaunchedEffect(Unit) {
        viewModel.getAnimeList()
    }

    Box(modifier = modifier) {
        when (uiState) {
            is AnimeListUiState.AnimeList -> {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize()
                ) {
                    (uiState as? AnimeListUiState.AnimeList)?.animeList?.let {
                        items(it.size) { index ->
                            AnimeListItem(anime = it[index]) { id ->
                                onAnimeClicked(id)
                            }
                        }
                    }
                }
            }

            AnimeListUiState.EmptyList -> ErrorScreen(text = "List not found")
            is AnimeListUiState.Error -> ErrorScreen(text = (uiState as? AnimeListUiState.Error)?.error.toString())
            AnimeListUiState.Loading -> LoadingScreen()
        }
    }

}


@Serializable
object ScreenAnimeList