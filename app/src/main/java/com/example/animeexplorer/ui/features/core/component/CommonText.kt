package com.example.animeexplorer.ui.features.core.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun CommonText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 12.sp,
    fontStyle: FontStyle = FontStyle.Normal,
    fontWeight: FontWeight = FontWeight.W500,
    textColor: Color = MaterialTheme.colorScheme.primary
) {
    Text(
        text = text,
        modifier = modifier,
        color = textColor,
        fontSize = fontSize,
        fontWeight = fontWeight,
        fontStyle = fontStyle,
    )
}