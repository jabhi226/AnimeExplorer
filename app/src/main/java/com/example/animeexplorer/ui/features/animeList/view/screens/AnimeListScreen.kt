package com.example.animeexplorer.ui.features.animeList.view.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.animeexplorer.R
import com.example.animeexplorer.domain.entities.Anime
import com.example.animeexplorer.ui.features.animeList.view.component.AnimeListItem
import com.example.animeexplorer.ui.features.animeList.viewmodel.AnimeListViewModel
import com.example.animeexplorer.ui.features.core.component.CommonImage
import com.example.animeexplorer.ui.features.core.component.CommonOutlinedTextField
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
    val searchedText by remember { viewModel.searchedText }
    var isShowSearchBox by remember { mutableStateOf(searchedText.isNotEmpty()) }

    LaunchedEffect(searchedText) {
        if (searchedText.isEmpty())
            viewModel.getAnimeListPaginated()
    }

    val scrollState = rememberLazyGridState()
    var lastScrollIndex by remember { mutableIntStateOf(scrollState.firstVisibleItemIndex) }
    var scrollDirection by remember { mutableStateOf(ScrollState.SCROLL_UP) }

    LaunchedEffect(scrollState.firstVisibleItemIndex) {
        val currentScrollIndex = scrollState.firstVisibleItemIndex
        scrollDirection = when {
            currentScrollIndex > lastScrollIndex -> ScrollState.SCROLL_DOWN
            currentScrollIndex < lastScrollIndex -> ScrollState.SCROLL_UP
            else -> ScrollState.IDLE
        }
        lastScrollIndex = currentScrollIndex
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        AnimatedVisibility(
            visible = (scrollDirection == ScrollState.SCROLL_UP || scrollState.firstVisibleItemIndex == 0),
        ) {
            SearchBar(
                searchedText = searchedText,
                isShowSearchBox = isShowSearchBox,
                showSearchBar = {
                    isShowSearchBox = it
                },
                updateText = {
                    viewModel.searchByText(text = it)
                })
        }

        LazyVerticalGrid(
            state = scrollState,
            columns = GridCells.Adaptive(minSize = 160.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp),
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
fun SearchBar(
    modifier: Modifier = Modifier,
    searchedText: String,
    isShowSearchBox: Boolean,
    showSearchBar: (Boolean) -> Unit,
    updateText: (String) -> Unit,
) {

    Box(contentAlignment = Alignment.Center) {
        Row(
            modifier = modifier
                .background(color = Color.Transparent)
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AnimatedVisibility(
                visible = !isShowSearchBox,
                enter = slideInHorizontally() + fadeIn(),
                exit = slideOutHorizontally() + fadeOut()
            ) {
                CommonText(
                    modifier = modifier.padding(8.dp),
                    text = "AnimeExplorer",
                    fontSize = 18.sp,
                    textColor = MaterialTheme.colorScheme.primary
                )
            }

            AnimatedVisibility(
                visible = !isShowSearchBox,
                enter = slideInHorizontally() + fadeIn(),
                exit = slideOutHorizontally() + fadeOut()
            ) {
                Box(
                    modifier = modifier
                        .clip(RoundedCornerShape(80.dp))
                        .clickable {
                            showSearchBar(true)
                        }
                        .padding(8.dp),
                ) {
                    CommonImage(
                        painter = painterResource(R.drawable.ic_search),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

        }



        AnimatedVisibility(
            visible = isShowSearchBox,
            enter = slideInHorizontally() + fadeIn(),
            exit = slideOutHorizontally() + fadeOut()
        ) {
            CommonOutlinedTextField(
                modifier = modifier.padding(horizontal = 8.dp),
                text = searchedText,
                updateText = {
                    updateText(it)
                },
                leadingIcon = {
                    Box(
                        modifier = modifier
                            .clip(RoundedCornerShape(80.dp))
                            .padding(8.dp),
                    ) {
                        CommonImage(
                            painter = painterResource(R.drawable.ic_search),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                trailingIcon = {
                    Box(
                        modifier = modifier
                            .clip(RoundedCornerShape(80.dp))
                            .clickable {
                                updateText("")
                                showSearchBar(false)
                            }
                            .padding(8.dp),
                    ) {
                        CommonImage(
                            painter = painterResource(android.R.drawable.ic_menu_close_clear_cancel),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
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
        LoadingScreen(isShowLoading = count == 0)
    } else {
        LoadingComponent()
    }
}

enum class ScrollState {
    SCROLL_UP,
    SCROLL_DOWN,
    IDLE
}

@Serializable
object ScreenAnimeList