package com.example.otchallenge.di

import com.example.otchallenge.di.scopes.AppScope
import com.example.otchallenge.networking.Secrets
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


@ContributesTo(AppScope::class)
@Module
object RetrofitModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()

        builder.addInterceptor { chain ->
            var request = chain.request()
            val url: HttpUrl = request.url().newBuilder()
                .addQueryParameter("api-key", Secrets.API_KEY).build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }
        return builder.build()
    }

    @Provides
    fun provideBaseRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl("https://api.nytimes.com/")
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
}
