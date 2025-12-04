package com.example.animeexplorer.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.animeexplorer.ui.features.animeDetails.view.screen.AnimeDetailScreen
import com.example.animeexplorer.ui.features.animeDetails.view.screen.ScreenAnimeDetail
import com.example.animeexplorer.ui.features.animeImages.view.screens.AnimeImagesScreen
import com.example.animeexplorer.ui.features.animeImages.view.screens.ScreenAnimeImage
import com.example.animeexplorer.ui.features.animeList.view.screens.AnimeListScreen
import com.example.animeexplorer.ui.features.animeList.view.screens.ScreenAnimeList

@Composable
fun AnimeExplorerApp(modifier: Modifier = Modifier) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ScreenAnimeList) {

        composable<ScreenAnimeList> {
            AnimeListScreen(
                modifier = modifier,
                onAnimeClicked = {
                    navController.navigate(ScreenAnimeDetail(it))
                })
        }
        composable<ScreenAnimeDetail> {
            val arg = it.toRoute<ScreenAnimeDetail>()

            AnimeDetailScreen(
                modifier = modifier,
                onAnimeImageClicked = { imageUrl ->
                    navController.navigate(ScreenAnimeImage(animeUrl = imageUrl))
                }
            )
        }
        composable<ScreenAnimeImage> {
            val arg = it.toRoute<ScreenAnimeImage>()
            AnimeImagesScreen(modifier = modifier, imageUrl = arg.animeUrl)
        }

    }
}