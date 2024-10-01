package com.example.otchallenge.networking

import com.example.otchallenge.networking.model.BooksResponse
import io.reactivex.rxjava3.core.Single

class FakeBooksApi : BooksApi {
    var onGetHardcoverFictionBooks: () -> Single<BooksResponse> = {
        Single.just(
            BooksResponse(
                BooksResponse.Results(emptyList())
            )
        )
    }

    override fun getHardcoverFictionBooks(): Single<BooksResponse> {
        return onGetHardcoverFictionBooks()
    }
}