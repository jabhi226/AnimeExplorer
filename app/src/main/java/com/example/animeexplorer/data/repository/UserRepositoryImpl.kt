package com.example.animeexplorer.data.repository


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.example.animeexplorer.data.preference.BOOKMARKED_LIST
import com.example.animeexplorer.data.preference.COMPLETED_LIST
import com.example.animeexplorer.data.preference.DataStoreHelper.dataStore
import com.example.animeexplorer.domain.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserRepositoryImpl(
    context: Context,
) : UserRepository {

    private val dataStore: DataStore<Preferences> = context.dataStore

    override fun getIsBookmarked(animeId: Int): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                // dataStore.data throws an IOException when an error is encountered when reading data
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val bookMarkedList = preferences[BOOKMARKED_LIST] ?: listOf()
                bookMarkedList.any { it == animeId.toString() }
            }
    }

    override suspend fun upsertBookmarked(animeId: String) {
        dataStore.edit { preferences ->
            val existingBookMarkedList = preferences[BOOKMARKED_LIST] ?: emptySet()
            preferences[BOOKMARKED_LIST] = existingBookMarkedList + animeId
        }
    }

    override suspend fun removeBookmarked(animeId: String) {
        dataStore.edit { preferences ->
            val existingBookMarkedList = preferences[BOOKMARKED_LIST] ?: emptySet()
            preferences[BOOKMARKED_LIST] = existingBookMarkedList - animeId
        }
    }

    override fun getIsCompleted(animeId: Int): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                // dataStore.data throws an IOException when an error is encountered when reading data
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val bookMarkedList = preferences[COMPLETED_LIST] ?: listOf()
                bookMarkedList.any { it == animeId.toString() }
            }
    }

    override suspend fun upsertCompleted(animeId: String) {
        dataStore.edit { preferences ->
            val existingCompletedList = preferences[COMPLETED_LIST] ?: emptySet()
            preferences[COMPLETED_LIST] = existingCompletedList + animeId
        }
    }

    override suspend fun removeCompleted(animeId: String) {
        dataStore.edit { preferences ->
            val existingCompletedList = preferences[COMPLETED_LIST] ?: emptySet()
            preferences[COMPLETED_LIST] = existingCompletedList - animeId
        }
    }

}