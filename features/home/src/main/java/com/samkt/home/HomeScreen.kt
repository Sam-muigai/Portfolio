package com.samkt.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun HomeScreen() {
    HomeScreenContent()
}


@Composable
fun HomeScreenContent(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Button(
                shape = MaterialTheme.shapes.extraSmall,
                onClick = {

                }
            ) {
                Text(
                    "Hello World",
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}