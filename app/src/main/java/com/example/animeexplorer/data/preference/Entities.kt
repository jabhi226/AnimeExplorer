package com.example.animeexplorer.data.preference

import androidx.datastore.preferences.core.stringSetPreferencesKey

val BOOKMARKED_LIST = stringSetPreferencesKey("bookmarked_list")
data class Bookmarked(val bookmarked: List<String>)


val COMPLETED_LIST = stringSetPreferencesKey("completed_list")
data class Completed(val completed: List<String>)