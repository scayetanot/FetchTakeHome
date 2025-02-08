package com.whatvr.fetchtakehome.repositories

import com.whatvr.fetchtakehome.data.network.FetchApi
import com.whatvr.fetchtakehome.repositories.mapper.Mapper.toHiringGroupedItem
import com.whatvr.fetchtakehome.repositories.model.HiringGrouped
import javax.inject.Inject

class FetchRepositoryImpl @Inject constructor(
    private val api: FetchApi
): FetchRepository {
    override suspend fun getListOfHiring(): Resource<List<HiringGrouped>> {
        return try {
            val response = api.getListOfHiring()
            if(response.isSuccessful) {
                response.body()?.let {
                    Resource.success(data = it.toHiringGroupedItem())
                } ?: Resource.error("response is empty", null)
            } else {
                Resource.error("Failed response", null)
            }
        } catch (e: Exception) {
            Resource.error(e.localizedMessage ?: "Unknown error", null)
        }
    }

}