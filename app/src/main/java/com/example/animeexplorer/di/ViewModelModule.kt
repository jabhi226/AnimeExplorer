package com.example.animeexplorer.di

import com.example.animeexplorer.ui.features.animeDetails.viewmodel.AnimeDetailViewModel
import com.example.animeexplorer.ui.features.animeList.viewmodel.AnimeListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { AnimeListViewModel(get()) }
    viewModel { AnimeDetailViewModel(get()) }

}