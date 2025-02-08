package com.whatvr.fetchtakehome.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.whatvr.fetchtakehome.repositories.FetchRepository
import com.whatvr.fetchtakehome.repositories.Status
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

    private fun fetchData() {
        viewModelScope.launch {
            val response = fetchRepository.getListOfHiring()
            when(response.status) {
                Status.SUCCESS -> {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            items = response.data ?: emptyList(),
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