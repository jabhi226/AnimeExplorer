package com.example.animeexplorer.ui.features.animeList.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.animeexplorer.R
import com.example.animeexplorer.domain.entities.Anime
import com.example.animeexplorer.ui.features.core.component.CommonAsyncImage
import com.example.animeexplorer.ui.features.core.component.CommonImage
import com.example.animeexplorer.ui.features.core.component.CommonText

@Composable
fun AnimeListItem(
    modifier: Modifier = Modifier,
    anime: Anime,
    onAnimeClicked: (Int) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth(1F)
            .clip(RoundedCornerShape(16.dp))
            .clickable {
                onAnimeClicked(anime.animeId)
            }
            .padding(8.dp)
    ) {
        Box {
            if (anime.posterUrl == null) {
                CommonImage(
                    modifier = Modifier.fillMaxWidth(1F),
                    painter = painterResource(R.drawable.ic_launcher_background)
                )
            } else {
                CommonAsyncImage(
                    modifier = Modifier
                        .background(
                            color = Color.Transparent,
                        )
                        .clip(RoundedCornerShape(16.dp))
                        .fillMaxWidth(1F)
                        .height(320.dp),
                    model = anime.posterUrl,
                    contentScale = ContentScale.Crop
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .background(
                        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.25F),
//                        shape = RoundedCornerShape(16.dp)
                        shape = RoundedCornerShape(topEnd = 16.dp, bottomStart = 16.dp)
                    )
                    .padding(vertical = 4.dp, horizontal = 6.dp)
            ) {
                CommonText(
                    text = "Ep ${anime.noOfEpisodes}",
                    textColor = MaterialTheme.colorScheme.onSurface
                )
            }

            anime.score?.let {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .background(
                            color = MaterialTheme.colorScheme.surface.copy(alpha = 0.25F),
//                            shape = RoundedCornerShape(16.dp)
                            shape = RoundedCornerShape(topStart = 16.dp, bottomEnd = 16.dp)
                        )
                        .padding(vertical = 4.dp, horizontal = 6.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        CommonText(
                            text = "$it",
                            textColor = MaterialTheme.colorScheme.onSurface
                        )
                        CommonImage(painter = painterResource(id = R.drawable.ic_star))
                    }
                }
            }
        }
        CommonText(
            modifier = Modifier
                .padding(vertical = 4.dp)
                .align(Alignment.CenterHorizontally),
            text = anime.animeName
        )
    }
}