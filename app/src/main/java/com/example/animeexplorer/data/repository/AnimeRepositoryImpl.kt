package com.example.animeexplorer.data.repository

import com.example.animeexplorer.data.mapper.getAnime
import com.example.animeexplorer.data.mapper.getAnimeDetails
import com.example.animeexplorer.data.dto.AnimeDetailsResponse
import com.example.animeexplorer.data.dto.AnimeListResponse
import com.example.animeexplorer.data.utils.HttpRoutes
import com.example.animeexplorer.data.utils.ResponseExtensions.parseClassOrError
import com.example.animeexplorer.domain.entities.Anime
import com.example.animeexplorer.domain.entities.AnimeDetails
import com.example.animeexplorer.domain.repository.AnimeRepository
import com.example.animeexplorer.domain.util.Response
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.url

class AnimeRepositoryImpl(private val httpClient: HttpClient) : AnimeRepository {

    override suspend fun getAnimeList(): Response<List<Anime>> {
        val response: Response<AnimeListResponse> = httpClient.get {
            url(HttpRoutes.ANIME_LIST_URL)
        }.parseClassOrError(AnimeListResponse::class)
        return if (response is Response.Error) {
            Response.error(response.error)
        } else {
            response.data!!.let { animeListResponse ->
                val animeList = animeListResponse.data
                    .filter { it.malId != null }
                    .map {
                        it.getAnime()
                    }
                Response.success(animeList)
            }
        }
    }

    override suspend fun getAnimeList(pageNumber: Int, limit: Int): Response<List<Anime>> {
        val response: Response<AnimeListResponse> = httpClient.get {
            url("${HttpRoutes.ANIME_LIST_URL}?page=$pageNumber&limit=$limit")
        }.parseClassOrError(AnimeListResponse::class)
        return if (response is Response.Error) {
            Response.error(response.error)
        } else {
            response.data!!.let { animeListResponse ->
                val animeList = animeListResponse.data
                    .filter { it.malId != null }
                    .map {
                        it.getAnime()
                    }
                Response.success(animeList)
            }
        }
    }

    override suspend fun getAnimeDetails(animeId: Int): Response<AnimeDetails> {
        val response: Response<AnimeDetailsResponse> = httpClient.get {
            url(HttpRoutes.ANIME_DETAIL_URL + "$animeId")
        }.parseClassOrError(AnimeDetailsResponse::class)
        return if (response is Response.Error) {
            Response.error(response.error)
        } else {
            val res =
                response.data?.data?.getAnimeDetails()
                    ?: return Response.error("Something went wrong")
            Response.success(res)
        }
    }

}