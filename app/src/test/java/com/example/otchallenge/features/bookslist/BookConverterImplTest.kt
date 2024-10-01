package com.example.otchallenge.features.bookslist

import androidx.test.core.app.ApplicationProvider
import com.example.otchallenge.model.Book
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner


private const val TITLE = "Title"
private const val DESCRIPTION = "Description"
private const val AUTHOR = "Author"
private const val URI = "uri://1"
private const val IMAGE_URL = "imageUrl"

@RunWith(RobolectricTestRunner::class)
class BookConverterImplTest {

    private val underTest =
        BookConverterImpl(appContext = ApplicationProvider.getApplicationContext())


    @Test
    fun convertToPresentationObject() {
        val presentationObject = underTest.convertToPresentationObject(
            Book(
                rank = 1,
                title = TITLE,
                description = DESCRIPTION,
                author = AUTHOR,
                bookUri = URI,
                bookImage = IMAGE_URL

            )
        )
        assertEquals(
            BookPresentationObject(
                title = TITLE,
                description = DESCRIPTION,
                author = "By $AUTHOR",
                bookUri = URI,
                bookImage = IMAGE_URL

            ), presentationObject
        )
    }
}