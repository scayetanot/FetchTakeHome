package com.whatvr.fetchtakehome.ui.screens

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.whatvr.fetchtakehome.R
import com.whatvr.fetchtakehome.ui.viewmodels.MainViewModel


enum class Navigation(@StringRes val title: Int) {
    Home(title = R.string.app_name),
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FetchTakeHomeApp(
    viewModel: MainViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.app_name)) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.LightGray
                )
            )
        },
        containerColor = Color.LightGray
    ) {
        innerPadding ->

        val uiState by viewModel.uiState.collectAsState()

        NavHost(
            navController = navController,
            startDestination = Navigation.Home.name,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(route = Navigation.Home.name) {
                PullToRefreshBox(
                    isRefreshing = uiState.isRefreshing,
                    onRefresh = { viewModel.onPullToRefreshTrigger()},
                ) {
                    Column {
                        Row {
                            Button(
                                onClick = {
                                    viewModel.filter(uiState.isFiltering)
                                }
                            ) {
                                Text("Filter: ${uiState.isFiltering}")
                            }
                            Button(
                                onClick = {
                                    viewModel.sorting(uiState.isSorting)
                                }
                            ) {
                                Text("Sorting: ${uiState.isSorting}")
                            }
                        }
                        FetchScreen(
                            isLoading = uiState.isLoading,
                            list = uiState.items,
                            error = uiState.error,
                        )
                    }

                }
            }
        }

    }
}