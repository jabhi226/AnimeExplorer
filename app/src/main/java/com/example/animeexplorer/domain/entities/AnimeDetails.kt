package com.example.animeexplorer.domain.entities

data class AnimeDetails(
    val animeId: Int,
    val animeTitle: String,
    val animeTitleAlt: String,
    val synopsis: String,
    val genres: List<String>,
    val trailerUrl: String?,
    val trailerImageUrl: String?,
    val posterUrl: String?,
    val noOfEpisodes: Int?,
    val duration: String?,
    val status: String?,
    val rating: Float?,
    val rank: Int?,
    val ratedBy: Int?,
    val mainCast: List<String>,
) {
    companion object {
        val mockObject = AnimeDetails(
            animeId = 38524,
            animeTitle = "Attack on Titan Season 3 Part 2",
            animeTitleAlt = "Shingeki no Kyojin Season 3 Part 2",
            synopsis = "Seeking to restore humanity's diminishing hope, the Survey Corps embark on a mission to retake Wall Maria, where the battle against the merciless \\\"Titans\\\" takes the stage once again.\\n\\nReturning to the tattered Shiganshina District that was once his home, Eren Yeager and the Corps find the town oddly unoccupied by Titans. Even after the outer gate is plugged, they strangely encounter no opposition. The mission progresses smoothly until Armin Arlert, highly suspicious of the enemy's absence, discovers distressing signs of a potential scheme against them. \\n\\nShingeki no Kyojin Season 3 Part 2 follows Eren as he vows to take back everything that was once his. Alongside him, the Survey Corps strive—through countless sacrifices—to carve a path towards victory and uncover the secrets locked away in the Yeager family's basement.\\n\\n[Written by MAL Rewrite]" ?: "NA",
            genres = listOf("Action", "Drama", "Suspense"),
            trailerUrl = "https://www.youtube.com/embed/hKHepjfj5Tw?enablejsapi=1&wmode=opaque&autoplay=1",
            trailerImageUrl = "https://img.youtube.com/vi/hKHepjfj5Tw/default.jpg",
            posterUrl = "https://cdn.myanimelist.net/images/anime/1517/100633.jpg",
            noOfEpisodes = 10,
            duration = "23 min per ep",
            status = "Finished Airing",
            rating = 9.05F,
            rank = 3,
            ratedBy = 1678539,
            mainCast = listOf()
        )
    }
}
