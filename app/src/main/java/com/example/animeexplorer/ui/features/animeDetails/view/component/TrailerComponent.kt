package com.example.animeexplorer.ui.features.animeDetails.view.component


import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.media3.common.MediaItem
import androidx.media3.common.MimeTypes
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.example.animeexplorer.ui.features.core.component.CommonImage

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun TrailerComponentPreview() {
    TrailerComponent(
        modifier = Modifier,
        trailerUrl = "https://www.youtube.com/embed/hKHepjfj5Tw?enablejsapi=1&wmode=opaque&autoplay=1",
        onDismiss = {}
    )
}

@Composable
fun TrailerComponent(
    modifier: Modifier,
    trailerUrl: String?,
    onDismiss: () -> Unit
) {

    Dialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        onDismissRequest = { onDismiss() }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
        ) {
            CommonImage(
                modifier = Modifier
                    .clickable {
                        onDismiss()
                    }
                    .height(48.dp)
                    .width(48.dp)
                    .align(Alignment.TopEnd)
                    .padding(8.dp),
                painter = painterResource(android.R.drawable.ic_notification_clear_all),
            )
            Box(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                TrailerVideoV2(
                    trailerUrl = trailerUrl
                )
            }
        }
    }

}

@Preview
@Composable
private fun TrailerCompoPreview(modifier: Modifier = Modifier) {
    TrailerVideoV2(
        modifier = modifier,
        trailerUrl = "https://www.youtube.com/embed/hKHepjfj5Tw?enablejsapi=1&wmode=opaque&autoplay=1"
    )
}

@Composable
fun TrailerVideoV2(
    modifier: Modifier = Modifier,
    trailerUrl: String?
) {
    val htmlData = """
            <html>
              <body style="margin:0;">
                <iframe 
                  width="100%" 
                  height="100%" 
                  src="$trailerUrl"
                  frameborder="0"
                  allow="autoplay; encrypted-media"
                  allowfullscreen>
                </iframe>
              </body>
            </html>
        """.trimIndent()
//    val context = LocalContext.current
//    val webView = WebView(context).apply {
//        webViewClient = object : WebViewClient() {}
//        settings.javaScriptEnabled = true
//        loadData(htmlData, "text/html", "utf-8")
////        loadUrl(trailerUrl!!)
//    }

//    Text(modifier = modifier.fillMaxWidth(), text = "fsdlfjasdf")
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            modifier = Modifier.heightIn(200.dp, 400.dp),
            factory = { context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    webViewClient = WebViewClient()
                    webChromeClient = WebChromeClient()
                    layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    loadData(htmlData, "text/html", "utf-8")
//                loadUrl(trailerUrl!!)
                }
            }
        )
    }
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
