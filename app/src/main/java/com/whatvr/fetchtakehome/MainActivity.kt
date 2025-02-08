package com.whatvr.fetchtakehome

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.whatvr.fetchtakehome.ui.screens.FetchTakeHomeApp
import com.whatvr.fetchtakehome.ui.theme.FetchTakeHomeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FetchTakeHomeTheme {
                FetchTakeHomeApp()
            }
        }
    }
}