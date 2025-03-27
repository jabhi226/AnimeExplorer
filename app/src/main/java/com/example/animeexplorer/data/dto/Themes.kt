package com.example.animeexplorer.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Themes(
    @SerialName("mal_id") var malId: Int? = null,
    @SerialName("type") var type: String? = null,
    @SerialName("name") var name: String? = null,
    @SerialName("url") var url: String? = null
)
