package com.example.animeexplorer.app

import android.app.Application
import com.example.animeexplorer.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AnimeExplorerApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(androidContext = this@AnimeExplorerApplication)
            modules(
                appModule
            )
        }
    }
}