package com.example.animeexplorer.ui.features.core.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.sp
import com.example.animeexplorer.R

@Preview
@Composable
fun TextPreview(modifier: Modifier = Modifier) {
    CommonText(
        text = "sdfsdf",
        modifier = modifier,
        fontSize = 26.sp,
        fontWeight = FontWeight.W700,
//        style = TextStyle(
//            lineHeight = TextUnit(value = 28F, type = TextUnitType.Sp),
//            letterSpacing = TextUnit(value = 1F, type = TextUnitType.Sp)
//        )
    )
}

@Composable
fun CommonText(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: TextUnit = 12.sp,
    fontStyle: FontStyle = FontStyle.Normal,
    fontWeight: FontWeight = FontWeight.W500,
    textColor: Color = MaterialTheme.colorScheme.primary,
    fontFamily: FontFamily = FontFamily(Font(R.font.lexend_medium)),
    style: TextStyle = TextStyle(letterSpacing = TextUnit(value = 0.8F, type = TextUnitType.Sp))
) {
    Text(
        text = text,
        modifier = modifier,
        color = textColor,
        fontSize = fontSize,
        fontWeight = fontWeight,
        fontStyle = fontStyle,
        fontFamily = fontFamily,
        style = style
    )
}