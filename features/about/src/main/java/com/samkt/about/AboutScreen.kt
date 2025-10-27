package com.samkt.about

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.samkt.domain.models.Experience
import com.samkt.domain.models.UserInformation
import org.koin.compose.viewmodel.koinViewModel
import kotlin.math.exp

@Composable
fun AboutScreen(
    aboutScreenViewModel: AboutScreenViewModel = koinViewModel(),
    onBackClick: () -> Unit
) {
    val aboutScreenUiState =
        aboutScreenViewModel.aboutScreenUiState.collectAsStateWithLifecycle().value
    AboutScreenContent(
        aboutScreenUiState = aboutScreenUiState,
        onBackClick = onBackClick
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreenContent(
    modifier: Modifier = Modifier,
    aboutScreenUiState: AboutScreenUiState,
    onBackClick: () -> Unit = {}
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        stringResource(R.string.about_me),
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
            targetState = aboutScreenUiState
        ) { aboutScreenUiState ->
            when (aboutScreenUiState) {
                is AboutScreenUiState.Error -> {
                    AboutScreenErrorScreen(
                        errorMessage = aboutScreenUiState.message
                    )
                }

                AboutScreenUiState.Loading -> {
                    AboutScreenLoadingScreen()
                }

                is AboutScreenUiState.Success -> {
                    AboutScreenContent(
                        userInformation = aboutScreenUiState.aboutMe.user,
                        experiences = aboutScreenUiState.aboutMe.experiences
                    )
                }
            }
        }
    }
}

@Composable
fun AboutScreenErrorScreen(
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
fun AboutScreenLoadingScreen(modifier: Modifier = Modifier) {
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
fun AboutScreenContent(
    modifier: Modifier = Modifier,
    userInformation: UserInformation? = null,
    experiences: List<Experience>? = null
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            AnimatedVisibility(userInformation != null) {
                Column(
                    modifier = modifier
                        .fillMaxWidth(),
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
                        userInformation!!.name,
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
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        item {
            AnimatedVisibility(
                userInformation != null
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            stringResource(R.string.about_me),
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        userInformation!!.about,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }

        when {
            experiences == null -> {}
            experiences.isEmpty() -> {
                item {
                    Text(stringResource(R.string.no_experience))
                }
            }

            else -> {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(
                            stringResource(R.string.experience),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
                items(experiences) {
                    ExperienceCard(experience = it)
                }
            }
        }
    }
}

@Composable
fun ExperienceCard(
    modifier: Modifier = Modifier,
    experience: Experience
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colorScheme.surfaceContainer,
            shape = MaterialTheme.shapes.small
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_outline_work),
                    contentDescription = experience.description
                )
            }
        }
        Spacer(Modifier.width(8.dp))
        Column {
            Text(
                text = "${experience.title} at ${experience.companyName}",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = "${experience.fromDate} - ${experience.toDate}",
                style = MaterialTheme.typography.labelSmall
            )
        }
    }
}