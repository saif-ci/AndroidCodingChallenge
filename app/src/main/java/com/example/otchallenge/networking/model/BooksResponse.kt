package com.example.otchallenge.networking.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class BooksResponse(
    @Json(name = "results") val results: Results
) {

    @JsonClass(generateAdapter = true)
    data class Results(
        @Json(name = "books") val books: List<BookApiModel>
    )
}