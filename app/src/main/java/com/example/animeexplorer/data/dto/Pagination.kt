package com.example.animeexplorer.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Pagination(
    @SerialName("last_visible_page") var lastVisiblePage: Int? = null,
    @SerialName("has_next_page") var hasNextPage: Boolean? = null,
    @SerialName("current_page") var currentPage: Int? = null,
    @SerialName("items") var items: Items? = Items()
)

@Serializable
data class Items(
    @SerialName("count") var count: Int? = null,
    @SerialName("total") var total: Int? = null,
    @SerialName("per_page") var perPage: Int? = null

)
