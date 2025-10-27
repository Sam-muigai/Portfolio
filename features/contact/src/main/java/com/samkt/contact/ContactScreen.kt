package com.samkt.contact

import androidx.compose.foundation.clickable
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel

@Composable
fun ContactScreen(
    contactScreenViewModel: ContactScreenViewModel = koinViewModel(),
    onBackClicked: () -> Unit
) {
    ContactScreenContent(
        onBackClicked = onBackClicked
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreenContent(
    modifier: Modifier = Modifier,
    onBackClicked: () -> Unit = {}
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            CenterAlignedTopAppBar(
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
            Text(
                "Other Ways to Connect",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            Spacer(Modifier.height(16.dp))
            SocialMediaCard(
                modifier = Modifier,
                label = "Email",
                trailingContent = {
                    Text(
                        "sammuigai880@gmail.com",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            )
            SocialMediaCard(
                modifier = Modifier,
                label = "Twitter",
                trailingContent = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Twitter"
                    )
                }
            )
            SocialMediaCard(
                modifier = Modifier,
                label = "LinkedIn",
                trailingContent = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "LinkedIn"
                    )
                }
            )
            SocialMediaCard(
                modifier = Modifier,
                label = "Github",
                trailingContent = {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "LinkedIn"
                    )
                }
            )
            Spacer(Modifier.height(64.dp))
        }
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
            .clickable(
                onClick = {
                    onClick(link)
                }
            )
            .fillMaxWidth()
            .height(72.dp)
            .padding(bottom = 16.dp),
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