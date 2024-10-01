package com.example.otchallenge.features.bookslist.presenter

import com.example.otchallenge.RxAndroidSchedulers
import com.example.otchallenge.features.bookslist.BookListContract
import com.example.otchallenge.features.bookslist.BookPresentationObject
import com.example.otchallenge.features.bookslist.FakeBookConverter
import com.example.otchallenge.model.Book
import com.example.otchallenge.repository.api.model.RepositoryResult
import com.example.otchallenge.repository.impl.FakeBooksRepository
import io.reactivex.rxjava3.schedulers.Schedulers
import okio.IOException
import org.junit.Assert.assertEquals
import com.example.otchallenge.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

private const val TITLE = "Title"
private const val DESCRIPTION = "Description"
private const val AUTHOR = "Author"
private const val URI = "uri://"
private const val IMAGE_URL = "imageUrl"
private const val PREFIX = "By "

class BooksListPresenterTest {

    @get:Rule
    val rxSchedulers = RxAndroidSchedulers()

    private val fakeBooksRepository = FakeBooksRepository()
    private val fakeBookConverter = FakeBookConverter(authorPrefix = PREFIX)

    private val view = object : BookListContract.View {
        var booksShown: List<BookPresentationObject> = emptyList()
            private set
        var errorMessageShown: Int? = null
            private set
        var loadingShown: Boolean = false
            private set

        override fun showBooks(books: List<BookPresentationObject>) {
            booksShown = books
        }

        override fun showError(errorMessage: Int) {
            errorMessageShown = errorMessage
        }

        override fun showLoading() {
            loadingShown = true
        }

        fun reset() {
            booksShown = emptyList()
            errorMessageShown = null
            loadingShown = false
        }

    }

    private val underTest = BooksListPresenter(
        booksRepository = fakeBooksRepository,
        bookConverter = fakeBookConverter
    )

    @Before
    fun setup() {
        rxSchedulers
            .setIoScheduler(Schedulers.trampoline())
            .setMainThreadScheduler(Schedulers.trampoline())
    }

    @Test
    fun attach_Fetching_Success() {
        fakeBooksRepository.repositoryResult = RepositoryResult.Success(books())

        underTest.attach(view)

        assertEquals(true, view.loadingShown)
        assertEquals(bookPresentationObjects(), view.booksShown)
        assertEquals(null, view.errorMessageShown)
    }

    @Test
    fun attach_Fetching_Failure_thenSuccess() {
        fakeBooksRepository.repositoryResult =
            RepositoryResult.Failure(IOException("doesn't matter"))

        underTest.attach(view)

        assertEquals(true, view.loadingShown)
        assertEquals(emptyList<BookPresentationObject>(), view.booksShown)
        assertEquals(R.string.book_fetch_error_message, view.errorMessageShown)

        underTest.detach()
        view.reset()
        fakeBooksRepository.repositoryResult = RepositoryResult.Success(books())

        underTest.attach(view)

        assertEquals(true, view.loadingShown)
        assertEquals(bookPresentationObjects(), view.booksShown)
        assertEquals(null, view.errorMessageShown)
    }

    private fun bookPresentationObjects() = buildList {
        repeat(5) { i ->
            add(
                BookPresentationObject(
                    title = "$TITLE $i",
                    description = "$DESCRIPTION $i",
                    author = "${PREFIX}$AUTHOR $i",
                    bookUri = "$URI$i",
                    bookImage = "$IMAGE_URL$i"

                )
            )
        }
    }

    private fun books() = buildList {
        repeat(5) { i ->
            add(
                Book(
                    rank = i + 1,
                    title = "$TITLE $i",
                    description = "$DESCRIPTION $i",
                    author = "$AUTHOR $i",
                    bookUri = "$URI$i",
                    bookImage = "$IMAGE_URL$i"

                )
            )
        }
    }
}