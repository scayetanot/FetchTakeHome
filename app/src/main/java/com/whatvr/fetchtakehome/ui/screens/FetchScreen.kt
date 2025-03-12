package com.whatvr.fetchtakehome.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.whatvr.fetchtakehome.repositories.model.HiringGrouped
import com.whatvr.fetchtakehome.ui.components.GroupIdCard


@Composable
fun FetchScreen(
    list: List<HiringGrouped>,
    error: String,
    isLoading: Boolean,
) {
    Box (modifier = Modifier.fillMaxSize()) {
        if (error.isNotEmpty()) {
            Toast.makeText(
                LocalContext.current,
                error,
                Toast.LENGTH_SHORT
            ).show()

        }
        if (isLoading) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.width(32.dp),
                    color = MaterialTheme.colorScheme.secondary,
                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                )
            }
        }

        LazyColumn() {
             list.forEach {
                item {
                    GroupIdCard(it.listId, it.items)
                }
            }
        }
    }

}