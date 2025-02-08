package com.whatvr.fetchtakehome.data.network.model

import com.google.gson.annotations.SerializedName

data class RetrofitItem(
    @SerializedName("id")
    val id: Int,
    @SerializedName("listId")
    val listId: Int,
    @SerializedName("name")
    val name: String?,
)
