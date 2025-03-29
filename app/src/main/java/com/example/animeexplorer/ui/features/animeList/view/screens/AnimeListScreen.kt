package com.example.animeexplorer.ui.features.animeList.view.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.animeexplorer.domain.entities.Anime
import com.example.animeexplorer.ui.features.animeList.view.component.AnimeListItem
import com.example.animeexplorer.ui.features.animeList.viewmodel.AnimeListViewModel
import com.example.animeexplorer.ui.features.core.component.CommonText
import com.example.animeexplorer.ui.features.core.component.LoadingComponent
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

    val animeList: LazyPagingItems<Anime> = viewModel.animes.collectAsLazyPagingItems()

    LaunchedEffect(Unit) {
        viewModel.getAnimeListPaginated()
    }

    Column(modifier = modifier) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1F)

        ) {
            items(animeList.itemCount) { index ->
                animeList[index]?.let {
                    AnimeListItem(anime = it) { id ->
                        onAnimeClicked(id)
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            animeList.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        ShowLoading(count = animeList.itemCount)
                    }

                    loadState.refresh is LoadState.Error -> {
                        val error = loadState.refresh as LoadState.Error
                        ShowError(count = animeList.itemCount, error.error.localizedMessage)
                    }

                    loadState.append is LoadState.Loading -> {
                        ShowLoading(count = animeList.itemCount)
                    }

                    loadState.append is LoadState.Error -> {
                        val error = loadState.append as LoadState.Error
                        ShowError(count = animeList.itemCount, error.error.localizedMessage)
                    }
                }
            }
        }


    }


}


@Composable
fun ShowError(count: Int, error: String?) {
    if (count == 0) {
        ErrorScreen(text = error ?: "")
    } else {
        CommonText(text = error ?: "")
    }

}

@Composable
fun ShowLoading(count: Int) {
    if (count == 0) {
        LoadingScreen()
    } else {
        LoadingComponent()
    }
}


@Serializable
object ScreenAnimeList