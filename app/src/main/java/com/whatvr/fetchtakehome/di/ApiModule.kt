package com.whatvr.fetchtakehome.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

class ApiModule {
    @InstallIn(SingletonComponent::class)
    @Module
    class APIModule {

        @Singleton
        @Provides
        @Named("default")
        fun provideDefaultOkHttpClient(): OkHttpClient =
            OkHttpClient
                .Builder()
                .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()



        @Singleton
        @Provides
        fun provideRetrofit(
            @Named("default") okHttpClient: OkHttpClient
        ): Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://fetch-hiring.s3.amazonaws.com/")
            .client(okHttpClient)
            .build()
    }
}