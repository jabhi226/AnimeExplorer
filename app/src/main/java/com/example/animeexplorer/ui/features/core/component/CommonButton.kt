package com.example.animeexplorer.ui.features.core.component

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Preview(showSystemUi = true)
@Composable
fun CommonButton(
    modifier: Modifier = Modifier,
    text: String = "",
    onClick: () -> Unit = {}
) {
    Button(
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
        onClick = onClick
    ) {
        CommonText(text = text, textColor = MaterialTheme.colorScheme.onPrimary)
    }
}