package com.example.animeexplorer.domain.repository

import com.example.animeexplorer.domain.entities.Anime
import com.example.animeexplorer.domain.entities.AnimeDetails
import com.example.animeexplorer.domain.util.Response

interface AnimeRepository {

    suspend fun getAnimeList(): Response<List<Anime>>

    suspend fun getAnimeDetails(animeId: Int): Response<AnimeDetails>

    suspend fun getAnimeList(pageNumber: Int, limit: Int): Response<List<Anime>>

    suspend fun getAnimeListByText(pageNumber: Int, limit: Int, text: String): Response<List<Anime>>

    suspend fun getAnimeImageList(animeId: Int): Response<List<String>>

}