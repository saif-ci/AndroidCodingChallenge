package com.example.otchallenge.features.bookslist.presenter

import android.util.Log
import com.example.otchallenge.R
import com.example.otchallenge.di.scopes.AppScope
import com.example.otchallenge.features.bookslist.BookConverter
import com.example.otchallenge.features.bookslist.BookListContract
import com.example.otchallenge.repository.api.BooksRepository
import com.example.otchallenge.repository.api.model.RepositoryResult
import com.squareup.anvil.annotations.ContributesBinding
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.plusAssign
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@ContributesBinding(AppScope::class)
class BooksListPresenter @Inject constructor(
    private val booksRepository: BooksRepository,
    private val bookConverter: BookConverter
) :
    BookListContract.Presenter {
    private var view: BookListContract.View? = null
    private val compositeDisposable = CompositeDisposable()

    override fun attach(view: BookListContract.View) {
        this.view = view
        fetchBooks()
    }

    private fun fetchBooks() {
        view?.showLoading()
        compositeDisposable += booksRepository.getBooks()
            .map { repositoryResult ->
                when (repositoryResult) {
                    is RepositoryResult.Success -> {
                        val books = repositoryResult.value
                        books.map { bookConverter.convertToPresentationObject(it) } to null
                    }

                    is RepositoryResult.Failure -> null to repositoryResult.failure
                }
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError { Log.e(TAG, "Unexpected error fetching books", it) }
            .subscribe { (books, _) ->
                if (books != null)
                    view?.showBooks(books)
                else
                    view?.showError(R.string.book_fetch_error_message)
            }
    }

    override fun detach() {
        compositeDisposable.clear()
        view = null
    }

    companion object {
        private val TAG = BooksListPresenter::class.java.simpleName
    }
}

