package com.example.animeexplorer.ui.features.core.component

import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animeexplorer.R

@Preview
@Composable
fun CommonOutlinedTextField(
    modifier: Modifier = Modifier,
    text: String = "",
    hint: String = "",
    singleLine: Boolean = true,
    maxCharacterLength: Int = 100,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default.copy(capitalization = KeyboardCapitalization.Sentences),
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    textStyle: TextStyle = TextStyle.Default,
    updateText: (String) -> Unit = {},
) {

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .focusable(true),
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        value = text,
        onValueChange = {
            if (it.length <= maxCharacterLength) {
                updateText(it)
            }
        },
        leadingIcon = { leadingIcon?.let { it() } },
        trailingIcon = { trailingIcon?.let { it() } },
        textStyle = textStyle.copy(
            fontFamily = FontFamily(Font(R.font.lexend_medium)),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 18.sp
        ),
        shape = RoundedCornerShape(16.dp),
    )
}