package com.example.otchallenge.features.bookslist

import android.content.Context
import com.example.otchallenge.R
import com.example.otchallenge.di.scopes.AppScope
import com.example.otchallenge.di.scopes.SingleIn
import com.example.otchallenge.model.Book
import com.squareup.anvil.annotations.ContributesBinding
import javax.inject.Inject

interface BookConverter {
    fun convertToPresentationObject(book: Book): BookPresentationObject
}

@SingleIn(AppScope::class)
@ContributesBinding(AppScope::class)
class BookConverterImpl @Inject constructor(private val appContext: Context) : BookConverter {
    override fun convertToPresentationObject(book: Book): BookPresentationObject {
        with(book) {
            return BookPresentationObject(
                title = title,
                description = description,
                author = appContext.getString(R.string.by_author, author),
                bookImage = bookImage,
                bookUri = bookUri
            )
        }
    }
}