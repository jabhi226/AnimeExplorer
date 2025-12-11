package com.example.animeexplorer.di

import com.example.animeexplorer.data.repository.AnimeRepositoryImpl
import com.example.animeexplorer.data.repository.UserRepositoryImpl
import com.example.animeexplorer.domain.repository.AnimeRepository
import com.example.animeexplorer.domain.repository.UserRepository
import org.koin.dsl.module


val repositoryModule = module {

    factory<AnimeRepository> { AnimeRepositoryImpl(get()) }
//    factory<AnimeRepository> { com.example.animeexplorer.data.repository.AnimeRepositoryImplTest() }


    factory<UserRepository> { UserRepositoryImpl(get()) }
//    factory<AnimeRepository> { com.example.animeexplorer.data.repository.AnimeRepositoryImplTest() }

}