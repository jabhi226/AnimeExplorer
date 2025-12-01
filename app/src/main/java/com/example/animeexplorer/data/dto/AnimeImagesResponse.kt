package com.example.animeexplorer.data.dto

import kotlinx.serialization.SerialName


class AnimeImages(
    @SerialName("data") var data: ArrayList<Data> = arrayListOf()
)

data class Data(

    @SerialName("jpg") var jpg: Jpg? = Jpg(),
    @SerialName("webp") var webp: Webp? = Webp()

)