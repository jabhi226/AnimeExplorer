package com.example.animeexplorer.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Trailer(

    @SerialName("youtube_id") var youtubeId: String? = null,
    @SerialName("url") var url: String? = null,
    @SerialName("embed_url") var embedUrl: String? = null,
    @SerialName("images") var images: TrailerImages? = TrailerImages()

)

@Serializable
data class TrailerImages(

    @SerialName("image_url") var imageUrl: String? = null,
    @SerialName("small_image_url") var smallImageUrl: String? = null,
    @SerialName("medium_image_url") var mediumImageUrl: String? = null,
    @SerialName("large_image_url") var largeImageUrl: String? = null,
    @SerialName("maximum_image_url") var maximumImageUrl: String? = null

)