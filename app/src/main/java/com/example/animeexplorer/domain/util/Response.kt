package com.example.animeexplorer.domain.util

sealed class Response<out T>(val data: T?) {

    data class Success<out T>(val value: T) : Response<T>(value)

    data class Error(val error: String) : Response<Nothing>(null)

    companion object {

        fun <T> success(value: T): Success<T> {
            return Success(value = value)
        }

        fun error(errorMessage: String?): Error {
            return Error(error = errorMessage ?: "Something went wrong")
        }
    }

}