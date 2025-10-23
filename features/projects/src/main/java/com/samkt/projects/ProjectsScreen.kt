package com.samkt.projects

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.samkt.domain.models.Project
import com.samkt.domain.models.UserInformation
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProjectsScreen(
    projectsScreenViewModel: ProjectsScreenViewModel = koinViewModel(),
    onBackClick: () -> Unit = {},
) {
    val projectsScreenUiState =
        projectsScreenViewModel.projectsScreenUiState.collectAsStateWithLifecycle().value

    ProjectScreenContent(
        projectsScreenUiState = projectsScreenUiState,
        onBackClick = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectScreenContent(
    modifier: Modifier = Modifier,
    projectsScreenUiState: ProjectsScreenUiState,
    onBackClick: () -> Unit = {},
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Projects",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClick,
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                            contentDescription = null
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        AnimatedContent(
            modifier = Modifier.padding(paddingValues),
            targetState = projectsScreenUiState,
        ) { projectsScreenUiState ->
            when (projectsScreenUiState) {
                is ProjectsScreenUiState.Error -> {
                    ProjectScreenErrorScreen(
                        errorMessage = projectsScreenUiState.message
                    )
                }

                ProjectsScreenUiState.Loading -> {
                    ProjectScreenLoadingScreen()
                }

                is ProjectsScreenUiState.Success -> {
                    ProjectScreenContent(projects = projectsScreenUiState.projects)
                }
            }
        }
    }
}

@Composable
fun ProjectScreenLoadingScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            strokeWidth = 1.5.dp
        )
    }
}

@Composable
fun ProjectScreenErrorScreen(
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
fun ProjectScreenContent(
    modifier: Modifier = Modifier,
    projects: List<Project>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(projects) {
            Text(it.title)
        }
    }
}