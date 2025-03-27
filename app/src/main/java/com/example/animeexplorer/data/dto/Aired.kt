package com.example.animeexplorer.data.dto


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Aired(
    @SerialName("from") var from: String? = null,
    @SerialName("to") var to: String? = null,
    @SerialName("prop") var prop: Prop? = Prop(),
    @SerialName("string") var string: String? = null
)

@Serializable
data class Prop(
    @SerialName("from") var from: From? = From(),
    @SerialName("to") var to: To? = To()

)

@Serializable
data class From(
    @SerialName("day") var day: Int? = null,
    @SerialName("month") var month: Int? = null,
    @SerialName("year") var year: Int? = null
)

@Serializable
data class To(
    @SerialName("day") var day: Int? = null,
    @SerialName("month") var month: Int? = null,
    @SerialName("year") var year: Int? = null
)
