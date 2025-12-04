package com.example.animeexplorer.utils

import android.content.Context
import android.widget.Toast
import androidx.compose.material3.Snackbar
import androidx.compose.runtime.Composable

@Composable
fun ShowSnackBar(message: String) {
    Snackbar() {

    }
}

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}