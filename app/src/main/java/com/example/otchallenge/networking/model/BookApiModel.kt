package com.example.otchallenge.networking.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookApiModel(
    @Json(name = "title") val title: String,
    @Json(name = "description") val description: String,
    @Json(name = "author") val author: String,
    @Json(name = "book_image") val bookImage: String,
    @Json(name = "rank") val rank: Int,
    @Json(name = "book_uri") val bookUri: String
)