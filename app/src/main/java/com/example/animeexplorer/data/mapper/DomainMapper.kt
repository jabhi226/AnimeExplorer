package com.example.animeexplorer.data.mapper

import com.example.animeexplorer.data.dto.AnimeDetailsResponse
import com.example.animeexplorer.data.dto.AnimeListResponse
import com.example.animeexplorer.domain.entities.Anime
import com.example.animeexplorer.domain.entities.AnimeDetails

fun AnimeListResponse.Data.getAnime(): Anime {
    return Anime(
        animeId = malId!!,
        animeName = titleEnglish ?: "NA",
        noOfEpisodes = episodes ?: 0,
        score = score?.toFloat(),
        posterUrl = images?.let { it.jpg?.imageUrl }
    )
}


fun AnimeDetailsResponse.Data.getAnimeDetails(): AnimeDetails? {
    try {
        return AnimeDetails(
            animeId = malId!!,
            animeTitle = titleEnglish ?: "NA",
            animeTitleAlt = title ?: "NA",
            synopsis = synopsis ?: "NA",
            genres = genres.map { it.name.toString() },
            trailerUrl = trailer?.url,
            posterUrl = images?.jpg?.imageUrl,
            noOfEpisodes = episodes,
            duration = duration,
            rating = score?.toFloat(),
            ratedBy = scoredBy ?: 0,
            mainCast = listOf()
        )
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }
}