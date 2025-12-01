package com.example.animeexplorer.ui.features.animeImages.view.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable

@Composable
fun AnimeImagesScreen(modifier: Modifier = Modifier, animeId: Int) {

}

@Serializable
data class ScreenAnimeImages(
    val animeId: Int
)