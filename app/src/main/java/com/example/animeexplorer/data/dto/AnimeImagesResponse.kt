package com.example.animeexplorer.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
class AnimeImages(
    @SerialName("data") var data: ArrayList<Data> = arrayListOf()
)

@Serializable
data class Data(
    @SerialName("jpg") var jpg: Jpg? = Jpg(),
    @SerialName("webp") var webp: Webp? = Webp()
)