package com.example.animeexplorer.domain.entities

data class Anime(
    val animeId: Int,
    val animeName: String,
    val noOfEpisodes: Int,
    val score: Float?,
    val posterUrl: String?
)