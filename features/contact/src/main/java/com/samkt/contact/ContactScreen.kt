package com.samkt.contact

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.samkt.domain.models.SocialMedia
import org.koin.androidx.compose.koinViewModel

@Composable
fun ContactScreen(
    contactScreenViewModel: ContactScreenViewModel = koinViewModel(),
    onBackClicked: () -> Unit
) {
    val contactScreenUiState =
        contactScreenViewModel.contactScreenUiState.collectAsStateWithLifecycle().value
    val context = LocalContext.current

    val uriHandler = LocalUriHandler.current
    ContactScreenContent(
        onBackClicked = onBackClicked,
        contactScreenUiState = contactScreenUiState,
        onSocialMediaClicked = { link ->
            uriHandler.openUri(link.formatLink())
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreenContent(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit = {},
    contactScreenUiState: ContactScreenUiState,
    onSocialMediaClicked: (link: String) -> Unit = {}
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                ),
                title = {
                    Text(
                        "Contact",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClicked,
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
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                "Let's Connect",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Spacer(Modifier.height(8.dp))
            Text(
                "I'm always open to discussing new projects, creative ideas, or just a friendly chat. Feel free to reach out through any of the channels below.",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(Modifier.height(16.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                shape = MaterialTheme.shapes.small,
                onValueChange = {},
                placeholder = {
                    Text(
                        "Your name",

                        )
                }
            )
            Spacer(Modifier.height(16.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                shape = MaterialTheme.shapes.small,
                onValueChange = {},
                placeholder = {
                    Text(
                        "Your Email",
                    )
                }
            )
            Spacer(Modifier.height(16.dp))
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                shape = MaterialTheme.shapes.small,
                minLines = 6,
                onValueChange = {},
                placeholder = {
                    Text(
                        "Your message",
                    )
                }
            )
            Spacer(Modifier.height(16.dp))
            Button(
                onClick = { /* Handle form submission */ },
                modifier = Modifier.fillMaxWidth(),
                shape = MaterialTheme.shapes.small
            ) {
                Text("Send Message", fontWeight = FontWeight.Bold)
            }
            Spacer(Modifier.height(16.dp))
            SocialMediaAccountContent(
                contactScreenUiState = contactScreenUiState,
                onSocialMediaClicked = onSocialMediaClicked
            )
        }
    }
}


@Composable
fun SocialMediaAccountContent(
    modifier: Modifier = Modifier,
    contactScreenUiState: ContactScreenUiState,
    onSocialMediaClicked: (link: String) -> Unit
) {
    AnimatedContent(
        modifier = modifier,
        targetState = contactScreenUiState
    ) { contactScreenState ->
        when (contactScreenState) {
            is ContactScreenUiState.Error -> Unit
            ContactScreenUiState.Loading -> {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(strokeWidth = 1.2.dp)
                }
            }

            is ContactScreenUiState.Success -> {
                SocialMediaSuccess(
                    socialMedia = contactScreenState.socialMedia,
                    onSocialMediaClicked = onSocialMediaClicked
                )
            }
        }
    }
}

@Composable
fun SocialMediaSuccess(
    modifier: Modifier = Modifier,
    socialMedia: SocialMedia,
    onSocialMediaClicked: (link: String) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            "Other Ways to Connect",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.ExtraBold
            )
        )
        Spacer(Modifier.height(16.dp))
        if (socialMedia.xUrl.isNotEmpty()) {
            SocialMediaCard(
                modifier = Modifier,
                label = "Twitter",
                trailingContent = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Twitter"
                    )
                },
                link = socialMedia.xUrl,
                onClick = onSocialMediaClicked
            )
        }
        if (socialMedia.linkedinUrl.isNotEmpty()) {
            SocialMediaCard(
                modifier = Modifier,
                label = "LinkedIn",
                trailingContent = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "LinkedIn"
                    )
                },
                link = socialMedia.linkedinUrl,
                onClick = onSocialMediaClicked
            )
        }
        if (socialMedia.githubUrl.isNotEmpty()) {
            SocialMediaCard(
                modifier = Modifier,
                label = "Github",
                trailingContent = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Github"
                    )
                },
                link = socialMedia.githubUrl,
                onClick = onSocialMediaClicked
            )
        }
        Spacer(Modifier.height(64.dp))
    }
}


@Composable
fun SocialMediaCard(
    modifier: Modifier = Modifier,
    label: String = "",
    link: String = "",
    trailingContent: (@Composable () -> Unit)? = {},
    onClick: (link: String) -> Unit = {}
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(72.dp)
            .padding(bottom = 16.dp)
            .clickable(
                onClick = {
                    onClick(link)
                }
            ),
        color = MaterialTheme.colorScheme.surfaceContainer,
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = label,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.bodyMedium
            )
            trailingContent?.invoke()
        }
    }
}