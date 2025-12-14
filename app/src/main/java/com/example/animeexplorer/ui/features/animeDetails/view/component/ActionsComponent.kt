package com.example.animeexplorer.ui.features.animeDetails.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.animeexplorer.R
import com.example.animeexplorer.ui.features.animeDetails.model.Action
import com.example.animeexplorer.ui.features.core.component.CommonImage
import com.example.animeexplorer.ui.features.core.component.CommonText
import com.example.animeexplorer.ui.theme.DarkColorScheme
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode

@Preview(
    showBackground = true
)
@Composable
fun ActionPre(modifier: Modifier = Modifier) {
    MaterialTheme(colorScheme = DarkColorScheme) {
        Box(modifier = Modifier.background(color = MaterialTheme.colorScheme.background)) {

            ActionsComponent(
                modifier = modifier,
                items =
                listOf(
                    Action(
                        id = 1,
                        label = "Watch Trailer",
                        imageId = R.drawable.ic_play,
                        isClickable = true
                    ),
                    Action(
                        id = 1,
                        label = "Bookmark",
                        imageId = R.drawable.ic_bookmark,
                        isClickable = false
                    ),
                    Action(
                        id = 1,
                        label = "8.95 Rating",
                        imageId = R.drawable.ic_star,
                        isClickable = true
                    ),
                    Action(
                        id = 1,
                        label = "Completed",
                        imageId = R.drawable.ic_check,
                        isClickable = false
                    ),
                    Action(
                        id = 1,
                        label = "Rank 3",
                        imageId = R.drawable.ic_star,
                        isClickable = true
                    ),
                )
            ) {

            }
        }

    }
}

@Composable
fun ActionsComponent(
    modifier: Modifier = Modifier,
    items: List<Action>,
    handleItemClick: (Action) -> Unit
) {
    FlowRow(
        modifier = modifier,
        mainAxisSize = SizeMode.Expand,
        mainAxisSpacing = 8.dp,
        crossAxisSpacing = 8.dp,
    ) {
        repeat(items.size) {
            val item = items[it]
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(
                        color = if (item.isClickable) MaterialTheme.colorScheme.surface else MaterialTheme.colorScheme.inverseSurface,
                        shape = RoundedCornerShape(8.dp)
                    )
                    .then(
                        Modifier.clickable {
                            handleItemClick(item)
                        }
                        /*if (item.isClickable) {
                        } else {
                            Modifier
                        }*/
                    )
                    .padding(8.dp)
                    .sizeIn(minHeight = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                CommonImage(
                    painter = painterResource(item.imageId),
                    tint = if (item.isClickable) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.inverseOnSurface
                )
                CommonText(
                    text = item.label,
                    textColor = if (item.isClickable) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.inverseOnSurface
                )
            }

        }
    }

}