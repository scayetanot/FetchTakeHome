package com.whatvr.fetchtakehome.ui.states

import com.whatvr.fetchtakehome.repositories.model.HiringGrouped

data class FetchUiState(
    val isLoading: Boolean = false,
    val items: List<HiringGrouped> = emptyList(),
    val error: String = "",
    val isRefreshing: Boolean = false,
    val isFiltering: Boolean = false,
    val isSorting: Boolean = false
)
