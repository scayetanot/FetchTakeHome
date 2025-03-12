package com.whatvr.fetchtakehome.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

        LazyColumn(
            modifier = Modifier.padding(horizontal = 12.dp)
        ) {
            list.forEach { listOfHiring ->
                item {
                    Column() {
                        HorizontalDivider(color = Color.Red, thickness = 2.dp)
                        Row {
                            Text(
                                "List ID:",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                            )
                            Spacer(Modifier.weight(1f).fillMaxWidth())
                            Text(
                                text = listOfHiring.listId.toString(),
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                            )
                        }
                    }
                }
                items(listOfHiring.items) { item ->
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp)
                    ) {
                        Text(
                            text = item.name,
                            modifier = Modifier
                                .padding(horizontal = 12.dp, vertical = 2.dp)
                        )
                    }
                }
                item {
                    HorizontalDivider(color = Color.Red, thickness = 2.dp)
                }
            }
           // items(
           //     items = list,
           //     item
           // )
        //     list.forEach {
        //        item {
        //            GroupIdCard(it.listId, it.items)
        //        }
        //    }
        }
    }

}