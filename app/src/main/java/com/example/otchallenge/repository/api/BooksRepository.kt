package com.example.otchallenge.repository.api

import com.example.otchallenge.model.Book
import com.example.otchallenge.repository.api.model.RepositoryResult
import io.reactivex.rxjava3.core.Single

interface BooksRepository {

    fun getBooks(): Single<RepositoryResult<List<Book>, Throwable>>
}