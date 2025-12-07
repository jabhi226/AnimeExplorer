package com.example.animeexplorer.di

import com.example.animeexplorer.data.repository.AnimeRepositoryImpl
import com.example.animeexplorer.domain.repository.AnimeRepository
import org.koin.dsl.module


val repositoryModule = module {

    factory<AnimeRepository> { AnimeRepositoryImpl(get()) }
//    factory<AnimeRepository> { com.example.animeexplorer.data.repository.AnimeRepositoryImplTest() }

}