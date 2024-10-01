package com.example.otchallenge.features.bookslist

import com.example.otchallenge.model.Book


class FakeBookConverter(private val authorPrefix: String) : BookConverter {
    override fun convertToPresentationObject(book: Book): BookPresentationObject {
        return with(book) {
            BookPresentationObject(
                title = title,
                description = description,
                author = authorPrefix + author,
                bookImage = bookImage,
                bookUri = bookUri
            )
        }
    }

}

