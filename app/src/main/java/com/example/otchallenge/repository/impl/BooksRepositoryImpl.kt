package com.example.otchallenge.repository.impl

import com.example.otchallenge.di.scopes.AppScope
import com.example.otchallenge.di.scopes.SingleIn
import com.example.otchallenge.model.Book
import com.example.otchallenge.networking.BooksApi
import com.example.otchallenge.networking.model.BookApiModel
import com.example.otchallenge.repository.api.BooksRepository
import com.example.otchallenge.repository.api.model.RepositoryResult
import com.squareup.anvil.annotations.ContributesBinding
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class BooksRepositoryImpl @Inject constructor(private val booksApi: BooksApi) :
    BooksRepository {

    override fun getBooks(): Single<RepositoryResult<List<Book>, Throwable>> {
        return booksApi.getHardcoverFictionBooks()
            .map { response -> RepositoryResult.Success(response.results.books.map { it.toDomainModel() }) as RepositoryResult<List<Book>, Throwable> }
            .onErrorReturn { RepositoryResult.Failure(it) }
    }

    private fun BookApiModel.toDomainModel(): Book {
        return Book(
            title = title,
            description = description,
            author = author,
            bookImage = bookImage,
            rank = rank,
            bookUri = bookUri
        )
    }
}