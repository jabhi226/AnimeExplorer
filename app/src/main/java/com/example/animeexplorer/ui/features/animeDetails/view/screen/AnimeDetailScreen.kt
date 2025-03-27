package com.example.animeexplorer.ui.features.animeDetails.view.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animeexplorer.R
import com.example.animeexplorer.ui.features.animeDetails.view.component.GenresComponent
import com.example.animeexplorer.ui.features.animeDetails.view.component.NumbersDetailsComponent
import com.example.animeexplorer.ui.features.animeDetails.view.component.TrailerComponent
import com.example.animeexplorer.ui.features.animeDetails.viewmodel.AnimeDetailViewModel
import com.example.animeexplorer.ui.features.animeDetails.viewmodel.AnimeDetailViewModel.AnimeDetailsUiState
import com.example.animeexplorer.ui.features.core.component.CommonImage
import com.example.animeexplorer.ui.features.core.component.CommonText
import com.example.animeexplorer.ui.features.core.screen.ErrorScreen
import com.example.animeexplorer.ui.features.core.screen.LoadingScreen
import com.google.accompanist.flowlayout.FlowRow
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
            .padding(8.dp)
    ) {
        when (uiState) {
            is AnimeDetailsUiState.AnimeDetailsState -> {
                val animeDetails = (uiState as? AnimeDetailsUiState.AnimeDetailsState)?.details
                if (animeDetails == null) {
                    ErrorScreen(text = "Cannot found the anime details")
                    return
                }
                LazyColumn(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    item {
                        TrailerComponent(
                            modifier = modifier
                                .fillMaxWidth()
                                .background(
                                    shape = RoundedCornerShape(16.dp),
                                    color = Color.Transparent
                                ),
                            trailerUrl = animeDetails.trailerUrl,
                            posterUrl = animeDetails.posterUrl,
                        )
                    }

                    item {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Column(
                                modifier = Modifier.weight(1F),
                                horizontalAlignment = Alignment.Start
                            ) {
                                CommonText(
                                    text = animeDetails.animeTitle,
                                    fontSize = 14.sp
                                )
                                CommonText(
                                    text = animeDetails.animeTitle,
                                    textColor = MaterialTheme.colorScheme.secondary,
                                )
                            }

                            animeDetails.rating?.let {
                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                                    ) {
                                        CommonText(
                                            text = "$it",
                                        )
                                        CommonImage(painter = painterResource(id = R.drawable.ic_star))
                                    }
                                    CommonText(
                                        text = "(${animeDetails.ratedBy})",
                                    )
                                }

                            }
                        }
                    }

                    item {


                        animeDetails.noOfEpisodes?.let {

                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                CommonImage(painter = painterResource(id = R.drawable.ic_book))
                                CommonText(
                                    text = "$it Episodes ${animeDetails.duration?.let { "($it)" }}",
                                )
                            }
                        }
                    }


                    item {
                        GenresComponent(animeDetails.genres)
                    }
                    item {
                        CommonText(modifier = Modifier.fillMaxWidth(), text = animeDetails.synopsis)
                    }
                    item {
                        Spacer(modifier = Modifier.height(20.dp))
                    }
                }
            }

            is AnimeDetailsUiState.Error -> ErrorScreen(text = (uiState as? AnimeDetailsUiState.Error)?.error.toString())
            AnimeDetailsUiState.Loading -> LoadingScreen()
        }
    }

}

@Serializable
data class ScreenAnimeDetail(
    val animeId: Int
)