package com.example.otchallenge.networking

import com.example.otchallenge.networking.model.BooksResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface BooksApi {
    @GET("svc/books/v3/lists/current/hardcover-fiction.json")
    fun getHardcoverFictionBooks(): Single<BooksResponse>
}