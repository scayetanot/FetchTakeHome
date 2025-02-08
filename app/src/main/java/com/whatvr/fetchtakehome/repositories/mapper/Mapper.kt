package com.whatvr.fetchtakehome.repositories.mapper

import com.whatvr.fetchtakehome.data.network.model.RetrofitItem
import com.whatvr.fetchtakehome.repositories.model.HiringGrouped
import com.whatvr.fetchtakehome.repositories.model.HiringItem

object Mapper {

    fun List<RetrofitItem>.toHiringGroupedItem(): List<HiringGrouped> {
        return this
            .filter { it.name.isNullOrEmpty().not() }
            .sortedBy { it.listId }
            .groupBy { it.listId }
            .map {
                HiringGrouped(
                    listId = it.key,
                    items = it.value.toHiringItem()
                )
            }
    }

    fun List<RetrofitItem>.toHiringItem(): List<HiringItem> {
        return this
            .map {
                HiringItem(
                    id = it.id,
                    listId = it.listId,
                    nameId = it.name?.toNameId() ?: Int.MAX_VALUE,
                    name = it.name?: ""
                )
            }
    }

    private fun String.toNameId(): Int =
        this.split(" ").last().toInt()

}