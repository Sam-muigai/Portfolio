package com.samkt.projects

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.samkt.domain.models.Project
import com.samkt.domain.models.UserInformation
import com.samkt.theme.PortfolioTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun ProjectsScreen(
    projectsScreenViewModel: ProjectsScreenViewModel = koinViewModel(),
    onBackClick: () -> Unit = {},
) {
    val projectsScreenUiState =
        projectsScreenViewModel.projectsScreenUiState.collectAsStateWithLifecycle().value
    val uriHandler = LocalUriHandler.current

    ProjectScreenContent(
        projectsScreenUiState = projectsScreenUiState,
        onBackClick = onBackClick,
        onViewDetails = { link ->
            uriHandler.openUri(link)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectScreenContent(
    modifier: Modifier = Modifier,
    projectsScreenUiState: ProjectsScreenUiState,
    onBackClick: () -> Unit = {},
    onViewDetails: (String) -> Unit = {}
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                title = {
                    Text(
                        stringResource(R.string.projects),
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
                    ProjectScreenContent(
                        projects = projectsScreenUiState.projects,
                        onViewDetails = onViewDetails
                    )
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
private fun ProjectScreenContent(
    modifier: Modifier = Modifier,
    projects: List<Project>,
    onViewDetails: (String) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(projects) { project ->
            ProjectCard(
                project = project,
                onViewDetails = {
                    onViewDetails.invoke(project.projectUrl)
                }
            )
        }
    }
}

@Composable
fun ProjectCard(
    modifier: Modifier = Modifier,
    project: Project,
    onViewDetails: () -> Unit = {}
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        color = MaterialTheme.colorScheme.background,
        shape = MaterialTheme.shapes.small,
        border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.outline)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top
            ) {
                Column(
                    modifier = Modifier.weight(0.3f)
                ) {
                    Text(
                        text = project.title,
                        style = MaterialTheme.typography.titleMedium
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = project.description,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
                Spacer(Modifier.width(8.dp))
                Surface(
                    modifier = Modifier.size(64.dp),
                    color = MaterialTheme.colorScheme.surface,
                    shape = MaterialTheme.shapes.small
                ) {

                }
            }
            Spacer(Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = stringResource(R.string.view_details),
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.primary
                    ),
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .clickable(onClick = onViewDetails)
                        .padding(8.dp)
                )
            }
        }
    }
}

@Preview
@Composable
private fun LoadingProjectScreenPreview() {
    PortfolioTheme {
        ProjectScreenContent(
            projectsScreenUiState = ProjectsScreenUiState.Loading
        )
    }
}

@Preview
@Composable
private fun ErrorProjectScreenPreview() {
    PortfolioTheme {
        ProjectScreenContent(
            projectsScreenUiState = ProjectsScreenUiState.Error("Error occurred")
        )
    }
}

@Preview
@Composable
private fun SuccessProjectScreenPreview() {
    PortfolioTheme {
        ProjectScreenContent(
            projectsScreenUiState = ProjectsScreenUiState.Success(
                listOf(
                    Project(
                        id = 1,
                        description = "Gameifying the learning experience",
                        imageUrl = "",
                        projectUrl = "test.com",
                        title = "Gameify"
                    )
                )
            )
        )
    }
}

