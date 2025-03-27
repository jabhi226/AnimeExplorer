package com.example.animeexplorer.ui.features.animeDetails.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.animeexplorer.ui.features.core.component.CommonText
import com.google.accompanist.flowlayout.FlowRow


@Composable
fun GenresComponent(
    genres: List<String>
) {
    FlowRow(
        modifier = Modifier.padding(16.dp),
        mainAxisSpacing = 8.dp,
        crossAxisSpacing = 8.dp,
    ) {
        repeat(genres.size) { index ->
            CommonText(
                modifier = Modifier
                    .padding(4.dp)
                    .background(
                        color = MaterialTheme.colorScheme.secondaryContainer,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(8.dp),
                text = genres[index],
                textColor = MaterialTheme.colorScheme.onSecondaryContainer
            )
        }
    }
}