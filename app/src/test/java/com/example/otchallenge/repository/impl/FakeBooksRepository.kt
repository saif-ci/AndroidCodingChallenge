package com.example.otchallenge.repository.impl

import com.example.otchallenge.model.Book
import com.example.otchallenge.repository.api.BooksRepository
import com.example.otchallenge.repository.api.model.RepositoryResult
import io.reactivex.rxjava3.core.Single

class FakeBooksRepository : BooksRepository {
    lateinit var repositoryResult: RepositoryResult<List<Book>, Throwable>

    override fun getBooks(): Single<RepositoryResult<List<Book>, Throwable>> {
        return Single.just(repositoryResult)
    }
}