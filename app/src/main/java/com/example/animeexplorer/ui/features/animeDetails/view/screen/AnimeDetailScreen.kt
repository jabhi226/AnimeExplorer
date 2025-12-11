package com.example.animeexplorer.ui.features.animeDetails.view.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animeexplorer.R
import com.example.animeexplorer.domain.entities.AnimeDetails
import com.example.animeexplorer.ui.features.animeDetails.model.Action
import com.example.animeexplorer.ui.features.animeDetails.view.component.ActionsComponent
import com.example.animeexplorer.ui.features.animeDetails.view.component.GenresComponent
import com.example.animeexplorer.ui.features.animeDetails.view.component.ImagesComponent
import com.example.animeexplorer.ui.features.animeDetails.view.component.TrailerComponent
import com.example.animeexplorer.ui.features.animeDetails.viewmodel.AnimeDetailViewModel
import com.example.animeexplorer.ui.features.animeDetails.viewmodel.AnimeDetailViewModel.AnimeDetailsUiState
import com.example.animeexplorer.ui.features.core.component.CommonText
import com.example.animeexplorer.ui.features.core.screen.ErrorScreen
import com.example.animeexplorer.ui.features.core.screen.LoadingScreen
import com.example.animeexplorer.utils.AppConstants
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Composable
fun AnimeDetailScreen(
    modifier: Modifier = Modifier,
    viewModel: AnimeDetailViewModel = koinViewModel<AnimeDetailViewModel>(),
    onAnimeImageClicked: (String) -> Unit
) {
    val uiState: AnimeDetailsUiState by viewModel.animeDetailsUiState.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        if (uiState.animeDetails != null) {
            val animeDetails = uiState.animeDetails
            if (animeDetails == null) {
                ErrorScreen(text = "Cannot found the anime details")
                return
            }
            AnimeDetails(
                modifier = Modifier
                    .fillMaxSize(),
                animeDetails = animeDetails,
                viewModel = viewModel,
            ) {
                onAnimeImageClicked(it)
            }
        }
        if (!uiState.error.isNullOrEmpty()) {
            ErrorScreen(text = uiState.error.toString())
        }
        LoadingScreen(isShowLoading = uiState.isLoading)
    }

}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_7)
@Composable
fun AnimeDetailPreview(modifier: Modifier = Modifier) {
    MaterialTheme {
        AnimeDetails(
            modifier = modifier,
            animeDetails = AnimeDetails.mockObject,
            viewModel = koinViewModel<AnimeDetailViewModel>(),
        ) {}
    }
}

@Composable
fun AnimeDetails(
    modifier: Modifier = Modifier,
    animeDetails: AnimeDetails,
    viewModel: AnimeDetailViewModel,
    onAnimeImageClicked: (String) -> Unit
) {

    var isShowTrailer by remember { mutableStateOf(false) }

    val isCompleted = viewModel.isCompleted?.collectAsState(false)
    val isBookmarked = viewModel.isBookmarked?.collectAsState(false)

    if (isShowTrailer) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            TrailerComponent(
                modifier = modifier.fillMaxSize(),
                trailerUrl = animeDetails.trailerUrl,
                onDismiss = {
                    isShowTrailer = false
                }
            )
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Box(
                modifier = Modifier
                    .fillParentMaxHeight(0.5F)
            ) {
                ImagesComponent(
                    modifier = modifier,
                    animeImages = animeDetails.posterUrl,
                    onAnimeImageClicked = {
                        onAnimeImageClicked(it)
                    }
                )
            }

        }

        item {
            CommonText(
                modifier = Modifier.fillMaxWidth(),
                text = animeDetails.animeTitle,
                fontSize = 26.sp,
                fontWeight = FontWeight.W700,
                style = TextStyle(
                    lineHeight = TextUnit(value = 28F, type = TextUnitType.Sp),
                    letterSpacing = TextUnit(value = 1F, type = TextUnitType.Sp)
                )
            )
        }

        item {
            Spacer(modifier = Modifier.height(8.dp))
            val actionList = arrayListOf(
                Action(
                    id = AppConstants.ACTION_TYPE_WATCH_TRAILER,
                    label = "Watch Trailer",
                    imageId = R.drawable.ic_play,
                    isClickable = true
                ),
                Action(
                    id = AppConstants.ACTION_TYPE_BOOKMARK,
                    label = if (isBookmarked?.value == true) {
                        "Bookmarked"
                    } else {
                        "Bookmark"
                    },
                    imageId = R.drawable.ic_bookmark,
                    isClickable = true
                ),
                Action(
                    id = AppConstants.ACTION_TYPE_COMPLETED_WATCHING,
                    label = if (isCompleted?.value == true) {
                        "Completed"
                    } else {
                        "Not Completed"
                    },
                    imageId = R.drawable.ic_check,
                    isClickable = true
                ),
            )
            animeDetails.status?.let { status ->
                actionList.add(
                    1,
                    Action(
                        id = AppConstants.ACTION_TYPE_IS_FINISHED,
                        label = status,
                        imageId = R.drawable.ic_finished,
                        //                        imageId = R.drawable.ic_incomplete,
                        isClickable = false
                    ),
                )
            }
            animeDetails.noOfEpisodes?.let {
                actionList.add(
                    1,
                    Action(
                        id = AppConstants.ACTION_TYPE_NO_OF_EPISODES,
                        label = "$it Episodes ${animeDetails.duration?.let { "($it)" }}",
                        imageId = R.drawable.ic_book,
                        isClickable = false
                    )
                )
            }
            animeDetails.rank?.let { rank ->
                if (rank < 30)
                    actionList.add(
                        1,
                        Action(
                            id = AppConstants.ACTION_TYPE_RANK,
                            label = "Rank $rank",
                            imageId = R.drawable.ic_star,
                            isClickable = false
                        )
                    )
            }
            animeDetails.rating?.let { rating ->
                actionList.add(
                    1,
                    Action(
                        id = AppConstants.ACTION_TYPE_RATING,
                        label = "$rating Ratings",
                        imageId = R.drawable.ic_star,
                        isClickable = false
                    )
                )
            }
            ActionsComponent(
                modifier = Modifier,
                items = actionList,
                handleItemClick = {
                    when (it.id) {
                        AppConstants.ACTION_TYPE_WATCH_TRAILER -> {
                            isShowTrailer = true
                        }

                        AppConstants.ACTION_TYPE_BOOKMARK -> {
                            viewModel.updateIsBookmarked(isBookmarked?.value, animeDetails.animeId.toString())
                        }

                        AppConstants.ACTION_TYPE_COMPLETED_WATCHING -> {
                            viewModel.updateIsCompleted(isCompleted?.value, animeDetails.animeId.toString())
                        }
                    }
                }
            )
        }

        item {
            CommonText(
                modifier = modifier,
                text = "Genres",
                fontSize = 26.sp,
                fontWeight = FontWeight.W700
            )
            Spacer(modifier = modifier.height(8.dp))
            GenresComponent(modifier = modifier, genres = animeDetails.genres)
        }

        item {
            Spacer(modifier = modifier.height(8.dp))
            CommonText(modifier = modifier.fillMaxWidth(), text = animeDetails.synopsis)
        }

        item {
            Spacer(modifier = modifier.height(24.dp))
        }
    }
}

@Serializable
data class ScreenAnimeDetail(
    val animeId: Int
)