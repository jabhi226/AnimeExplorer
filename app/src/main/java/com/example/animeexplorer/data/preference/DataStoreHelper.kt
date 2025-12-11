package com.example.animeexplorer.data.preference


import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

object DataStoreHelper {

    private const val USER_PREFERENCES = "user_preferences"

    val Context.dataStore by preferencesDataStore(
        name = USER_PREFERENCES
    )

}