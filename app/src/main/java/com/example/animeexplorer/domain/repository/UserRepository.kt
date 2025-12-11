package com.example.animeexplorer.domain.repository

import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getIsBookmarked(animeId: Int): Flow<Boolean>

    suspend fun upsertBookmarked(animeId: String)

    suspend fun removeBookmarked(animeId: String)

    fun getIsCompleted(animeId: Int): Flow<Boolean>

    suspend fun upsertCompleted(animeId: String)

    suspend fun removeCompleted(animeId: String)

}