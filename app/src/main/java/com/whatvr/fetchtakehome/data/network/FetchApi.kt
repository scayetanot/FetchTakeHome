package com.whatvr.fetchtakehome.data.network

import com.whatvr.fetchtakehome.data.network.model.RetrofitItem
import retrofit2.Response
import retrofit2.http.GET

interface FetchApi {
    @GET("hiring.json")
    suspend fun getListOfHiring(): Response<List<RetrofitItem>>
}