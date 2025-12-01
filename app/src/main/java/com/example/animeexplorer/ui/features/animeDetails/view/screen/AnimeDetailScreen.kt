package com.example.animeexplorer.ui.features.animeDetails.view.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
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
import com.example.animeexplorer.ui.features.animeDetails.view.component.TrailerComponent
import com.example.animeexplorer.ui.features.animeDetails.viewmodel.AnimeDetailViewModel
import com.example.animeexplorer.ui.features.animeDetails.viewmodel.AnimeDetailViewModel.AnimeDetailsUiState
import com.example.animeexplorer.ui.features.core.component.CommonText
import com.example.animeexplorer.ui.features.core.screen.ErrorScreen
import com.example.animeexplorer.ui.features.core.screen.LoadingScreen
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

@Composable
fun AnimeDetailScreen(modifier: Modifier = Modifier, animeId: Int) {

    val viewModel = koinViewModel<AnimeDetailViewModel>()
    val uiState by viewModel.animeDetailsUiState.collectAsState(initial = AnimeDetailsUiState.Loading)

    LaunchedEffect(animeId) {
        viewModel.getAnimeDetails(animeId)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        when (uiState) {
            is AnimeDetailsUiState.AnimeDetailsState -> {
                val animeDetails = (uiState as? AnimeDetailsUiState.AnimeDetailsState)?.details
                if (animeDetails == null) {
                    ErrorScreen(text = "Cannot found the anime details")
                    return
                }
                AnimeDetails(modifier = Modifier, animeDetails = animeDetails)
            }

            is AnimeDetailsUiState.Error -> ErrorScreen(text = (uiState as? AnimeDetailsUiState.Error)?.error.toString())
            AnimeDetailsUiState.Loading -> LoadingScreen()
        }
    }

}

@Preview(showBackground = true, showSystemUi = true, device = Devices.PIXEL_7)
@Composable
fun AnimeDetailPreview(modifier: Modifier = Modifier) {
    MaterialTheme {
        AnimeDetails(
            modifier = modifier,
            animeDetails = AnimeDetails.mockObject
        )
    }
}

@Composable
fun AnimeDetails(
    modifier: Modifier = Modifier,
    animeDetails: AnimeDetails
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Box(
                modifier = modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center,
            ) {
                TrailerComponent(
                    modifier = modifier
                        .fillMaxWidth(0.75F),
                    trailerUrl = animeDetails.trailerUrl,
                    trailerImageUrl = animeDetails.trailerImageUrl ?: animeDetails.posterUrl,
                    posterUrl = animeDetails.posterUrl,
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
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
            }
        }

        item {
//            CommonText(text = "Genres", fontSize = 24.sp)
            Spacer(modifier = Modifier.height(8.dp))
            val actionList = arrayListOf(
                Action(
                    id = 1,
                    label = "Watch Trailer",
                    imageId = R.drawable.ic_play,
                    isActive = false
                ),
                Action(
                    id = 1,
                    label = "Bookmark",
                    imageId = R.drawable.ic_bookmark,
                    isActive = true
                ),
                Action(
                    id = 1,
                    label = "Completed",
                    imageId = R.drawable.ic_check,
                    isActive = true
                ),
            )
            animeDetails.status?.let { status ->
                actionList.add(
                    1,
                    Action(
                        id = 1,
                        label = status,
                        imageId = R.drawable.ic_finished,
//                        imageId = R.drawable.ic_incomplete,
                        isActive = false
                    ),
                )
            }
            animeDetails.noOfEpisodes?.let {
                actionList.add(
                    1,
                    Action(
                        id = 1,
                        label = "$it Episodes ${animeDetails.duration?.let { "($it)" }}",
                        imageId = R.drawable.ic_book,
                        isActive = false
                    )
                )
            }
            animeDetails.rank?.let { rank ->
                if (rank < 30)
                    actionList.add(
                        1,
                        Action(
                            id = 1,
                            label = "Rank $rank",
                            imageId = R.drawable.ic_star,
                            isActive = false
                        )
                    )
            }
            animeDetails.rating?.let { rating ->
                actionList.add(
                    1,
                    Action(
                        id = 1,
                        label = "$rating Ratings",
                        imageId = R.drawable.ic_star,
                        isActive = false
                    )
                )
            }
            ActionsComponent(
                modifier = Modifier, items = actionList
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
//            CommonText(text = "Overview", fontSize = 24.sp)
            Spacer(modifier = Modifier.height(8.dp))
            CommonText(modifier = Modifier.fillMaxWidth(), text = animeDetails.synopsis)
        }
        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Serializable
data class ScreenAnimeDetail(
    val animeId: Int
)