package com.example.animeexplorer.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Images(
    @SerialName("jpg") var jpg: Jpg? = Jpg(),
    @SerialName("webp") var webp: Webp? = Webp()
)

@Serializable
data class Jpg(
    @SerialName("image_url") var imageUrl: String? = null,
    @SerialName("small_image_url") var smallImageUrl: String? = null,
    @SerialName("large_image_url") var largeImageUrl: String? = null
)

@Serializable
data class Webp(
    @SerialName("image_url") var imageUrl: String? = null,
    @SerialName("small_image_url") var smallImageUrl: String? = null,
    @SerialName("large_image_url") var largeImageUrl: String? = null
)