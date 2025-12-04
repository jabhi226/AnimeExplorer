package com.example.animeexplorer.ui.features.animeImages.view.screens

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.serialization.Serializable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.animeexplorer.R
import com.example.animeexplorer.utils.showToast
import com.example.animeexplorer.ui.features.core.component.CommonAsyncImage
import com.example.animeexplorer.ui.features.core.component.CommonImage
import com.example.animeexplorer.ui.features.core.component.CommonText
import com.example.animeexplorer.utils.AppDownloadManager

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun AnimeImgPreview(modifier: Modifier = Modifier) {
    AnimeImagesScreen(imageUrl = "")
}

@Composable
fun AnimeImagesScreen(
    modifier: Modifier = Modifier,
    imageUrl: String,
) {
    val context = LocalContext.current
    val downloadManager = AppDownloadManager()

    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            downloadManager.downloadImage(context, imageUrl)
        } else {
            context.showToast("Permission denied")
        }
    }

    fun handleDownloadButton() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // Android 13+ → No permission needed
            downloadManager.downloadImage(context, imageUrl)
        } else {
            // Android 12 and below → Need WRITE permission
            permissionLauncher.launch(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        CommonAsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = imageUrl
        )

        /*CommonImage(
            modifier = Modifier
                .fillMaxSize(),
            painter = painterResource(id = R.mipmap.full_metal),
            contentScale = ContentScale.Fit
        )*/

        Row(
            modifier = Modifier
                .align(alignment = Alignment.BottomEnd)
                .padding(32.dp)
                .clickable {
                    handleDownloadButton()
                }
                .background(
                    color = MaterialTheme.colorScheme.inverseSurface,
                    shape = RoundedCornerShape(8.dp),
                )
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CommonImage(
                painter = painterResource(R.drawable.ic_play),
                tint = MaterialTheme.colorScheme.inverseOnSurface
            )
            CommonText(
                fontSize = 16.sp,
                text = "Download",
                textColor = MaterialTheme.colorScheme.inverseOnSurface
            )
        }
    }


}

@Composable
fun AnimeImages(modifier: Modifier, animeImages: String) {

}


@Serializable
data class ScreenAnimeImage(
    val animeUrl: String
)