package com.example.animeexplorer.ui.features.animeDetails.view.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animeexplorer.ui.features.core.component.CommonText


@Preview(showBackground = true)
@Composable
fun NumbersDetailsComponent(
    modifier: Modifier = Modifier,
    title: String = "Score",
    rating: String = "9.12",
    detail: String = "234234 usersd xkljsdlf",
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        CommonText(
            text = title,
            textColor = MaterialTheme.colorScheme.onSecondaryContainer,
            modifier = modifier
                .background(
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(vertical = 4.dp, horizontal = 8.dp)
        )
        CommonText(
            text = rating,
            modifier = modifier
                .padding(horizontal = 8.dp)
                .padding(top = 4.dp),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        CommonText(
            text = detail,
            modifier = modifier.padding(8.dp),
            fontSize = 10.sp,
            textColor = MaterialTheme.colorScheme.secondary
        )
    }

}