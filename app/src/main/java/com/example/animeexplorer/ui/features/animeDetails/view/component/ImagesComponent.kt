package com.example.animeexplorer.ui.features.animeDetails.view.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.carousel.HorizontalCenteredHeroCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animeexplorer.ui.features.core.component.CommonAsyncImage

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ImageComPreviwe(modifier: Modifier = Modifier) {
    ImagesComponent(animeImages = mutableSetOf("", "")) {}

    ImagesComponent(
        modifier = Modifier,
        animeImages = mutableSetOf("", ""),
        onAnimeImageClicked = {
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImagesComponent(
    modifier: Modifier = Modifier,
    animeImages: MutableSet<String>,
    onAnimeImageClicked: (String) -> Unit
) {

    val carouselItems = remember { animeImages.toList() }

    HorizontalCenteredHeroCarousel(
        state = rememberCarouselState { carouselItems.count() },
        modifier = modifier,
        itemSpacing = 8.dp,
        contentPadding = PaddingValues(horizontal = 16.dp),
    ) { i ->
        val item = carouselItems[i]

        CommonAsyncImage(
            modifier = Modifier
                .clickable {
                    onAnimeImageClicked(item)
                }
                .fillMaxSize()
//                    .wrapContentHeight(align = Alignment.CenterVertically)
                .clip(RoundedCornerShape(16.dp)),
            model = item,
            contentScale = ContentScale.Fit
        )

        /*CommonImage(
            modifier = modifier
                .fillMaxSize()
//                .align(Alignment.Center)
                .clip(RoundedCornerShape(16.dp)),
            painter = painterResource(id = R.mipmap.full_metal),
            contentScale = ContentScale.Fit
        )*/
    }

}