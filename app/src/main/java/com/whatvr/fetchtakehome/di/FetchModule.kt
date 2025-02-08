package com.whatvr.fetchtakehome.di

import com.whatvr.fetchtakehome.data.network.FetchApi
import com.whatvr.fetchtakehome.repositories.FetchRepository
import com.whatvr.fetchtakehome.repositories.FetchRepositoryImpl

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class FetchModule {

    @Provides
    @Singleton
    fun provideFetchApi(retrofit: Retrofit): FetchApi {
        return retrofit.create(FetchApi::class.java)
    }
    @Singleton
    @Provides
    fun provideFetchRepository(
        api: FetchApi,
    ): FetchRepository {
        return FetchRepositoryImpl(api)
    }
}