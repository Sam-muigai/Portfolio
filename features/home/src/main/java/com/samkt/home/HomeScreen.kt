package com.samkt.home

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.samkt.domain.models.UserInformation
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = koinViewModel()
) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Portfolio",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                actions = {
                    IconButton(
                        onClick = { },
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Settings,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        AnimatedContent(
            modifier = Modifier.padding(paddingValues),
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
                    HomeScreenContent(
                        userInformation = homeScreenUiState.userInformation
                    )
                }
            }
        }
    }

}


@Composable
fun HomeScreenLoadingScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}


@Composable
fun HomeScreenErrorScreen(
    modifier: Modifier = Modifier,
    errorMessage: String
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            errorMessage,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
    userInformation: UserInformation
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .size(96.dp)
                .clip(
                    CircleShape
                )
                .background(MaterialTheme.colorScheme.onBackground)
        )
        Text(
            userInformation.name,
            style = MaterialTheme.typography.titleLarge.copy(
                fontWeight = FontWeight.Bold
            )
        )
        Text(
            userInformation.role,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Light
            )
        )
        Text(
            userInformation.country,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Light
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(
                "Quick Access",
                fontWeight = FontWeight.Bold
            )
        }
        QuickAccessCard(
            icon = Icons.Default.MailOutline,
            label = "Projects"
        )
        QuickAccessCard(
            icon = Icons.Default.Person,
            label = "About Me"
        )
        QuickAccessCard(
            icon = Icons.Default.Call,
            label = "Contact"
        )
    }
}

@Composable
fun QuickAccessCard(
    modifier: Modifier = Modifier,
    label: String,
    icon: ImageVector
) {
    Surface(
        modifier = modifier.fillMaxWidth()
            .padding(bottom = 8.dp),
        color = MaterialTheme.colorScheme.surfaceContainer,
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier,
                color = MaterialTheme.colorScheme.surfaceVariant.copy(
                    alpha = 0.7f
                ),
                shape = MaterialTheme.shapes.small
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .padding(8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null
                    )
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                label,
                fontWeight = FontWeight.Bold
            )
        }
    }
}