package com.example.animeexplorer.data.dto



import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Titles(

    @SerialName("type") var type: String? = null,
    @SerialName("title") var title: String? = null

)

@Serializable
data class Broadcast(

    @SerialName("day") var day: String? = null,
    @SerialName("time") var time: String? = null,
    @SerialName("timezone") var timezone: String? = null,
    @SerialName("string") var string: String? = null

)

@Serializable
data class Producers(

    @SerialName("mal_id") var malId: Int? = null,
    @SerialName("type") var type: String? = null,
    @SerialName("name") var name: String? = null,
    @SerialName("url") var url: String? = null

)

@Serializable
data class Licensors(

    @SerialName("mal_id") var malId: Int? = null,
    @SerialName("type") var type: String? = null,
    @SerialName("name") var name: String? = null,
    @SerialName("url") var url: String? = null

)

@Serializable
data class Studios(

    @SerialName("mal_id") var malId: Int? = null,
    @SerialName("type") var type: String? = null,
    @SerialName("name") var name: String? = null,
    @SerialName("url") var url: String? = null

)

@Serializable
data class Genres(

    @SerialName("mal_id") var malId: Int? = null,
    @SerialName("type") var type: String? = null,
    @SerialName("name") var name: String? = null,
    @SerialName("url") var url: String? = null

)

@Serializable
data class Demographics(

    @SerialName("mal_id") var malId: Int? = null,
    @SerialName("type") var type: String? = null,
    @SerialName("name") var name: String? = null,
    @SerialName("url") var url: String? = null

)