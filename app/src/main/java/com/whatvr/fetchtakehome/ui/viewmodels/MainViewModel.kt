package com.whatvr.fetchtakehome.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whatvr.fetchtakehome.repositories.FetchRepository
import com.whatvr.fetchtakehome.repositories.Status
import com.whatvr.fetchtakehome.repositories.model.HiringGrouped
import com.whatvr.fetchtakehome.repositories.model.HiringItem
import com.whatvr.fetchtakehome.ui.states.FetchUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val fetchRepository: FetchRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FetchUiState())
    val uiState = _uiState.asStateFlow()

    var listOfHired : List<HiringGrouped> = mutableListOf()

    init {
        showLoading()
        fetchData()
    }

    fun onPullToRefreshTrigger() {
        _uiState.update {
            it.copy(
                isRefreshing = true,
                isLoading = true
            )
        }
        viewModelScope.launch {
            fetchData()
        }
    }

    fun filter(enable: Boolean) {
        _uiState.update {
            it.copy(
                isFiltering = !enable
            )
        }
        filterAndSort(enable, _uiState.value.isSorting)
    }

    fun sorting(enable: Boolean) {
        _uiState.update {
            it.copy(
                isSorting = !enable
            )
        }
        filterAndSort(_uiState.value.isFiltering, enable)
    }

    //The goal of filterAndSort is to allow the user to filter and sort
    private fun filterAndSort(filter: Boolean, sort: Boolean) {
        _uiState.update {
            it.copy(
                items = listOfHired
                    .filterIt(filter)
                    .sortIt(sort)
            )
        }
    }

    private  fun List<HiringGrouped>.filterIt(enable: Boolean) : List<HiringGrouped> {
        return if(enable) {
             this.map {
                HiringGrouped(
                    it.listId,
                    it.items.filter { it.name.isEmpty().not() }
                )
            }
        } else {
            this
        }
    }

    private  fun List<HiringGrouped>.sortIt(enable: Boolean) : List<HiringGrouped> {
        return if(enable) {
            this.sortedBy { it.listId }
        } else {
            this
        }
    }

    private fun fetchData() {
        viewModelScope.launch {
            val response = fetchRepository.getListOfHiring()
            when(response.status) {
                Status.SUCCESS -> {
                    listOfHired = response.data ?: emptyList()
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            items = listOfHired,
                            error = if(response.data.isNullOrEmpty()) "Empty response" else "",
                            isRefreshing = false)
                    }
                }
                Status.ERROR -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            error = response.message ?: "Unknown error",
                            isRefreshing = false)
                    }
                }
            }
        }
    }

    private fun showLoading() {
        _uiState.update {
            it.copy(isLoading = true)
        }
    }
}