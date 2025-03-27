package com.example.animeexplorer

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.animeexplorer.ui.AnimeExplorerApp
import com.example.animeexplorer.ui.theme.AnimeExplorerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowManager.LayoutParams.FLAG_SECURE
        setContent {
            AnimeExplorerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AnimeExplorerApp(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}