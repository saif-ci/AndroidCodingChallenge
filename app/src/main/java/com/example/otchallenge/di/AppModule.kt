package com.example.otchallenge.di

import android.app.Application
import android.content.Context
import com.example.otchallenge.di.scopes.AppScope
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides

@ContributesTo(AppScope::class)
@Module
object AppModule {

    @Provides
    fun provideApplicationContext(app: Application): Context {
        return app
    }


}