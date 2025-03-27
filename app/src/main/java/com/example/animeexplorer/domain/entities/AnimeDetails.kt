package com.example.animeexplorer.domain.entities

data class AnimeDetails(
    val animeId: Int,
    val animeTitle: String,
    val animeTitleAlt: String,
    val synopsis: String,
    val genres: List<String>,
    val trailerUrl: String?,
    val posterUrl: String?,
    val noOfEpisodes: Int?,
    val duration: String?,
    val rating: Float?,
    val ratedBy: Int?,
    val mainCast: List<String>,
)
