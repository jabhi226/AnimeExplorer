package com.example.animeexplorer.ui.features.core.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale

@Composable
fun CommonImage(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String? = null,
    tint: Color? = null,
    contentScale: ContentScale = ContentScale.Fit,
    onClick: () -> Unit = {}
) {
    Image(
        modifier = modifier.clickable {
            onClick()
        },
        contentDescription = contentDescription,
        painter = painter,
        colorFilter = tint?.let { ColorFilter.tint(it) },
        contentScale = contentScale
    )
}