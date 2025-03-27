package com.example.animeexplorer.ui.features.animeDetails.view.component


import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.example.animeexplorer.ui.features.core.component.CommonAsyncImage


@Composable
fun TrailerComponent(
    modifier: Modifier = Modifier,
    trailerUrl: String?,
    posterUrl: String?
) {

    if (trailerUrl != null) {
        TrailerVideoV2(modifier = modifier, trailerUrl = trailerUrl)
    } else if (posterUrl != null) {
        CommonAsyncImage(modifier = modifier, model = posterUrl, contentScale = ContentScale.Crop)
    } else {

    }

}


@Composable
fun TrailerVideoV2(
    modifier: Modifier = Modifier,
    trailerUrl: String
) {

    val context = LocalContext.current
    val webView = WebView(context).apply {
        webViewClient = object : WebViewClient() {}
        settings.javaScriptEnabled = true
        loadUrl(trailerUrl)
    }

    AndroidView(
        factory = { webView },
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
    )
}

@OptIn(UnstableApi::class)
@Composable
fun TrailerVideo(
    modifier: Modifier = Modifier,
    trailerUrl: String
) {

    val context = LocalContext.current

    val player = remember {
        ExoPlayer.Builder(context).build()
    }

    LaunchedEffect(trailerUrl) {
        val mediaItem = MediaItem.Builder()
            .setUri(trailerUrl)
            .setMimeType(MimeTypes.VIDEO_MP4)
            .build()


        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()

    }

    Box(modifier = modifier) {
        AndroidView(
            factory = { context ->
                PlayerView(context).apply {
                    this.player = player
                    useController = true
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                    setControllerHideOnTouch(true)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
