package com.example.animeexplorer.ui.features.core.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.AsyncImage

@Composable
fun CommonAsyncImage(
    modifier: Modifier = Modifier,
    model: Any,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Fit,
) {
    CoilImageLoader(
        modifier = modifier,
        model = model,
        contentDescription = contentDescription,
        contentScale = contentScale
    )
}

@Composable
fun CoilImageLoader(
    model: Any,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Fit,
) {
    AsyncImage(
        modifier = modifier,
        model = model,
        contentDescription = contentDescription,
        contentScale = contentScale
    )
}