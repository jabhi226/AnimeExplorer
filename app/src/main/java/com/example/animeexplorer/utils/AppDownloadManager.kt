package com.example.animeexplorer.utils

import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment

class AppDownloadManager {

    fun downloadImage(context: Context, imageUrl: String) {
        val fileName = "${System.currentTimeMillis()}.jpg"

        try {
            val request = DownloadManager.Request(Uri.parse(imageUrl))
                .setMimeType("image/jpeg")
                .setTitle("Downloading image")
                .setDescription("Saving image...")
                .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                .setDestinationInExternalPublicDir(
                    Environment.DIRECTORY_PICTURES,
                    fileName
                )

            val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
            downloadManager.enqueue(request)
            context.showToast("Downloading...")

        } catch (e: Exception) {
            e.printStackTrace()
            context.showToast("Error downloading image")
        }
    }


}