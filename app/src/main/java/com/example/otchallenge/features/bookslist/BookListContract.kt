package com.example.otchallenge.features.bookslist

import androidx.annotation.StringRes
import com.example.otchallenge.core.mvp.BasePresenter

interface BookListContract {
    interface View {
        fun showBooks(books: List<BookPresentationObject>)
        fun showError(@StringRes errorMessage: Int)
        fun showLoading()

    }

    interface Presenter : BasePresenter<View>
}