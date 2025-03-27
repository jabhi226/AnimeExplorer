package com.example.animeexplorer.data.utils

import com.example.animeexplorer.domain.util.Response
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import kotlin.reflect.KClass

object ResponseExtensions {

    @OptIn(InternalSerializationApi::class)
    suspend fun <T : Any> HttpResponse.parseListOrError(type: KClass<T>): Response<List<T>> {
        return if (this.status == HttpStatusCode.OK) {
            try {
                val listSerializer = ListSerializer(type.serializer())
                val body: List<T> = Json.decodeFromString(listSerializer, this.bodyAsText())
                Response.success(body)
            } catch (e: Exception) {
                Response.error(e.message)
            }
        } else {
            Response.error("Request failed with status: ${this.status}")
        }
    }


    @OptIn(InternalSerializationApi::class)
    suspend fun <T : Any> HttpResponse.parseClassOrError(type: KClass<T>): Response<T> {
        return if (this.status == HttpStatusCode.OK) {
            try {
                val body: T = Json.decodeFromString(type.serializer(), this.bodyAsText())
                Response.success(body)
            } catch (e: Exception) {
                e.printStackTrace()
                Response.error(e.message)
            }
        } else {
            Response.error("Request failed with status: ${this.status}")
        }
    }


}