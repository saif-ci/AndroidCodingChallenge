package com.example.otchallenge.repository.impl

import com.example.otchallenge.model.Book
import com.example.otchallenge.networking.FakeBooksApi
import com.example.otchallenge.networking.model.BookApiModel
import com.example.otchallenge.networking.model.BooksResponse
import com.example.otchallenge.repository.api.model.RepositoryResult
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observers.TestObserver
import org.junit.Test
import java.io.IOException


private const val TITLE = "Title"
private const val DESCRIPTION = "Description"
private const val AUTHOR = "Author"
private const val URI = "uri://"
private const val IMAGE_URL = "imageUrl"

class BooksRepositoryImplTest {

    private val fakeBooksApi = FakeBooksApi()
    private val underTest = BooksRepositoryImpl(fakeBooksApi)

    private var observer = TestObserver.create<RepositoryResult<List<Book>, Throwable>>()

    @Test
    fun getBooks_Success_Error() {
        val exception = IOException("whoops!")
        fakeBooksApi.onGetHardcoverFictionBooks = {
            Single.error(exception)
        }

        underTest.getBooks().subscribe(observer)

        observer.assertValueCount(1)
        observer.assertValue { it == RepositoryResult.Failure(exception) }
    }


    @Test
    fun getBooks_Success_Empty() {
        underTest.getBooks().subscribe(observer)

        observer.assertValueCount(1)
        observer.assertValue { it == RepositoryResult.Success(value = emptyList<Book>()) }
    }

    @Test
    fun getBooks_Success_NotEmpty() {
        fakeBooksApi.onGetHardcoverFictionBooks = {
            Single.just(
                BooksResponse(
                    BooksResponse.Results(apiBooks())
                )
            )
        }

        underTest.getBooks().subscribe(observer)

        observer.assertValueCount(1)
        observer.assertValue { it == RepositoryResult.Success(value = books()) }

    }

    private fun apiBooks() = buildList {
        repeat(5) { i ->
            add(
                BookApiModel(
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