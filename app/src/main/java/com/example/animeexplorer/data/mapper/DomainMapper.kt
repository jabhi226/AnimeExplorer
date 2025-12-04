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

        val thimbnil = if (trailer?.youtubeId != null) {
            "https://img.youtube.com/vi/${trailer?.youtubeId}/hqdefault.jpg"
        } else {
            trailer?.images?.imageUrl ?: images?.jpg?.imageUrl
        }
        return AnimeDetails(
            animeId = malId!!,
            animeTitle = titleEnglish ?: "NA",
            animeTitleAlt = title ?: "NA",
            synopsis = synopsis ?: "NA",
            genres = genres.map { it.name.toString() },
            trailerUrl = trailer?.embedUrl ?: "https://www.youtube.com/embed/${trailer?.youtubeId}?autoplay=0&modestbranding=1",// trailer?.embedUrl,
            trailerImageUrl = thimbnil,
            posterUrl = images?.jpg?.largeImageUrl?.let { mutableSetOf(it) } ?: mutableSetOf(),
            noOfEpisodes = episodes,
            duration = duration,
            status = status,
            rating = score?.toFloat(),
            rank = rank,
            ratedBy = scoredBy ?: 0,
            mainCast = listOf()
        )
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }
}