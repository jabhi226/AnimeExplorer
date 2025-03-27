package com.example.animeexplorer.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AnimeDetailsResponse(
    @SerialName("data") var data: Data,
) {

    @Serializable
    data class Data(
        @SerialName("mal_id") var malId: Int? = null,
        @SerialName("url") var url: String? = null,
        @SerialName("images") var images: Images? = Images(),
        @SerialName("trailer") var trailer: Trailer? = Trailer(),
        @SerialName("approved") var approved: Boolean? = null,
        @SerialName("titles") var titles: List<Titles> = listOf(),
        @SerialName("title") var title: String? = null,
        @SerialName("title_english") var titleEnglish: String? = null,
        @SerialName("title_japanese") var titleJapanese: String? = null,
        @SerialName("title_synonyms") var titleSynonyms: List<String> = listOf(),
        @SerialName("type") var type: String? = null,
        @SerialName("source") var source: String? = null,
        @SerialName("episodes") var episodes: Int? = null,
        @SerialName("status") var status: String? = null,
        @SerialName("airing") var airing: Boolean? = null,
        @SerialName("aired") var aired: Aired? = Aired(),
        @SerialName("duration") var duration: String? = null,
        @SerialName("rating") var rating: String? = null,
        @SerialName("score") var score: Double? = null,
        @SerialName("scored_by") var scoredBy: Int? = null,
        @SerialName("rank") var rank: Int? = null,
        @SerialName("popularity") var popularity: Int? = null,
        @SerialName("members") var members: Int? = null,
        @SerialName("favorites") var favorites: Int? = null,
        @SerialName("synopsis") var synopsis: String? = null,
        @SerialName("background") var background: String? = null,
        @SerialName("season") var season: String? = null,
        @SerialName("year") var year: Int? = null,
        @SerialName("broadcast") var broadcast: Broadcast? = Broadcast(),
        @SerialName("producers") var producers: List<Producers> = listOf(),
        @SerialName("licensors") var licensors: List<Licensors> = listOf(),
        @SerialName("studios") var studios: List<Studios> = listOf(),
        @SerialName("genres") var genres: List<Genres> = listOf(),
        @SerialName("explicit_genres") var explicitGenres: List<String> = listOf(),
        @SerialName("themes") var themes: List<Themes> = listOf(),
        @SerialName("demographics") var demographics: List<Demographics> = listOf()
    )

}
