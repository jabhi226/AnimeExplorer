package com.example.animeexplorer.ui.features.core.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun CommonImage(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String? = null,
    tint: Color? = null
) {
    Image(
        modifier = modifier,
        contentDescription = contentDescription,
        painter = painter,
        colorFilter = tint?.let { ColorFilter.tint(it) }
    )
}