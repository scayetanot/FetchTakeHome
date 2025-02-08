package com.whatvr.fetchtakehome.repositories

import com.whatvr.fetchtakehome.repositories.model.HiringGrouped

interface FetchRepository {
    suspend fun getListOfHiring(): Resource<List<HiringGrouped>>
}