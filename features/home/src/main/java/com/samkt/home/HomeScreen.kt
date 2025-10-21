package com.samkt.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.samkt.domain.models.UserInformation
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = koinViewModel()
) {

    AnimatedContent(
        targetState = homeScreenViewModel.homeScreenUiState.collectAsState().value,
    ) { homeScreenUiState ->
        when (homeScreenUiState) {
            is HomeScreenUiState.Error -> {
                HomeScreenErrorScreen(
                    errorMessage = homeScreenUiState.message
                )
            }

            HomeScreenUiState.Loading -> {
                HomeScreenLoadingScreen()
            }

            is HomeScreenUiState.Success -> {
                HomeScreenContent(userInformation = homeScreenUiState.userInformation)
            }
        }
    }

}


@Composable
fun HomeScreenLoadingScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}

@Composable
fun HomeScreenErrorScreen(
    modifier: Modifier = Modifier,
    errorMessage: String
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(
                errorMessage,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    userInformation: UserInformation
) {
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
            Text(
                userInformation.name,
                fontWeight = FontWeight.Bold
            )
        }
    }
}