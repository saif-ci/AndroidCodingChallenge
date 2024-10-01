package com.example.otchallenge.networking.di

import com.example.otchallenge.di.scopes.AppScope
import com.example.otchallenge.networking.BooksApi
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@ContributesTo(AppScope::class)
@Module
object BooksApiModule {
    @Provides
    fun provideBooksApi(retrofit: Retrofit): BooksApi {
        return retrofit.create(BooksApi::class.java)
    }
}
