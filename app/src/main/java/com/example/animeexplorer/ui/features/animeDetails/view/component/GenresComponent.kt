package com.example.animeexplorer.ui.features.animeDetails.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animeexplorer.ui.features.core.component.CommonText
import com.google.accompanist.flowlayout.FlowRow


@Preview(showSystemUi = true)
@Composable
fun GeneresPreview() {
    GenresComponent(
        genres = listOf(
            "sdlkfjslf",
            "sdfkjsldfkj",
            "sdlkfjslf",
            "sdfkjsldfkj",
            "sdlkfjslf",
            "sdfkjsldfkj",
        )
    )
}

@Composable
fun GenresComponent(
    modifier: Modifier = Modifier,
    genres: List<String>
) {
    FlowRow(
        modifier = modifier,
        mainAxisSpacing = 8.dp,
        crossAxisSpacing = 8.dp,
    ) {
        repeat(genres.size) { index ->
            CommonText(
                modifier = Modifier
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color(0xFF8D4CFF), // soft purple
                                Color(0xFFFF68CE), // bright pink
//                                Color(0xFFFFB28A),  // peach highlight
                            )
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(8.dp),
                text = genres[index],
                textColor = Color.White
            )
        }
    }
}