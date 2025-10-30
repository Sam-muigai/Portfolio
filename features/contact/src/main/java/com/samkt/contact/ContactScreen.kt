package com.samkt.contact

import android.widget.Toast
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.samkt.domain.models.SocialMedia
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.compose.koinViewModel

@Composable
fun ContactScreen(
  contactScreenViewModel: ContactScreenViewModel = koinViewModel(),
  onBackClicked: () -> Unit,
) {
  val contactScreenUiState =
    contactScreenViewModel.contactScreenUiState.collectAsStateWithLifecycle().value

  val uriHandler = LocalUriHandler.current
  val context = LocalContext.current

  LaunchedEffect(contactScreenViewModel.responseMessage) {
    contactScreenViewModel.responseMessage.collectLatest { message ->
      Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }
  }

  ContactScreenContent(
    onBackClicked = onBackClicked,
    contactScreenUiState = contactScreenUiState,
    onSocialMediaClicked = { link ->
      uriHandler.openUri(link.formatLink())
    },
    email = contactScreenViewModel.email,
    onEmailChange = contactScreenViewModel::onEmailChange,
    name = contactScreenViewModel.name,
    onNameChange = contactScreenViewModel::onNameChange,
    message = contactScreenViewModel.message,
    onMessageChange = contactScreenViewModel::onMessageChange,
    onSendMessageClicked = contactScreenViewModel::onSendMessage,
    isSendingMessage = contactScreenViewModel.isSendingMessage,
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContactScreenContent(
  modifier: Modifier = Modifier,
  onBackClicked: () -> Unit = {},
  contactScreenUiState: ContactScreenUiState,
  onSocialMediaClicked: (link: String) -> Unit = {},
  email: String,
  onEmailChange: (String) -> Unit = {},
  name: String,
  onNameChange: (String) -> Unit = {},
  message: String,
  onMessageChange: (String) -> Unit = {},
  onSendMessageClicked: () -> Unit = {},
  isSendingMessage: Boolean = false,
) {
  Scaffold(
    modifier = modifier
      .fillMaxSize(),
    containerColor = MaterialTheme.colorScheme.background,
    topBar = {
      CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
          containerColor = MaterialTheme.colorScheme.background,
        ),
        title = {
          Text(
            stringResource(R.string.contact),
            style = MaterialTheme.typography.bodyLarge.copy(
              fontWeight = FontWeight.Bold,
            ),
          )
        },
        navigationIcon = {
          IconButton(
            onClick = onBackClicked,
          ) {
            Icon(
              imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
              contentDescription = null,
            )
          }
        },
      )
    },
  ) { paddingValues ->
    Column(
      modifier = Modifier
        .padding(paddingValues)
        .padding(16.dp)
        .fillMaxSize()
        .verticalScroll(rememberScrollState()),
    ) {
      Text(
        stringResource(R.string.lets_connect),
        style = MaterialTheme.typography.bodyLarge.copy(
          fontWeight = FontWeight.ExtraBold,
        ),
      )
      Spacer(Modifier.height(8.dp))
      Text(
        stringResource(R.string.open_for_discussion),
        style = MaterialTheme.typography.bodyMedium,
      )
      Spacer(Modifier.height(16.dp))
      OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = name,
        shape = MaterialTheme.shapes.small,
        onValueChange = onNameChange,
        placeholder = {
          Text(stringResource(R.string.your_name))
        },
      )
      Spacer(Modifier.height(16.dp))
      OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = email,
        shape = MaterialTheme.shapes.small,
        onValueChange = onEmailChange,
        placeholder = {
          Text(stringResource(R.string.your_email))
        },
      )
      Spacer(Modifier.height(16.dp))
      OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = message,
        shape = MaterialTheme.shapes.small,
        minLines = 6,
        onValueChange = onMessageChange,
        placeholder = {
          Text(stringResource(R.string.your_message))
        },
      )
      Spacer(Modifier.height(16.dp))
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
      ) {
        AnimatedContent(
          targetState = isSendingMessage,
        ) { isLoading ->
          if (isLoading) {
            CircularProgressIndicator(
              strokeWidth = 1.2.dp,
              modifier = Modifier.size(40.dp),
            )
          } else {
            Button(
              onClick = onSendMessageClicked,
              modifier = Modifier.fillMaxWidth(),
              shape = MaterialTheme.shapes.small,
            ) {
              Text(
                stringResource(R.string.send_message),
                fontWeight = FontWeight.Bold,
              )
            }
          }
        }
      }
      Spacer(Modifier.height(16.dp))
      SocialMediaAccountContent(
        contactScreenUiState = contactScreenUiState,
        onSocialMediaClicked = onSocialMediaClicked,
      )
    }
  }
}

@Composable
fun SocialMediaAccountContent(
  modifier: Modifier = Modifier,
  contactScreenUiState: ContactScreenUiState,
  onSocialMediaClicked: (link: String) -> Unit,
) {
  AnimatedContent(
    modifier = modifier,
    targetState = contactScreenUiState,
  ) { contactScreenState ->
    when (contactScreenState) {
      is ContactScreenUiState.Error -> Unit
      ContactScreenUiState.Loading -> {
        Row(
          modifier = Modifier.fillMaxWidth(),
          horizontalArrangement = Arrangement.Center,
        ) {
          CircularProgressIndicator(strokeWidth = 1.2.dp)
        }
      }

      is ContactScreenUiState.Success -> {
        SocialMediaSuccess(
          socialMedia = contactScreenState.socialMedia,
          onSocialMediaClicked = onSocialMediaClicked,
        )
      }
    }
  }
}

@Composable
fun SocialMediaSuccess(
  modifier: Modifier = Modifier,
  socialMedia: SocialMedia,
  onSocialMediaClicked: (link: String) -> Unit,
) {
  Column(
    modifier = modifier,
  ) {
    Text(
      stringResource(R.string.other_ways),
      style = MaterialTheme.typography.bodyLarge.copy(
        fontWeight = FontWeight.ExtraBold,
      ),
    )
    Spacer(Modifier.height(16.dp))
    if (socialMedia.xUrl.isNotEmpty()) {
      SocialMediaCard(
        modifier = Modifier,
        label = stringResource(R.string.twitter),
        trailingContent = {
          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = stringResource(R.string.twitter),
          )
        },
        link = socialMedia.xUrl,
        onClick = onSocialMediaClicked,
      )
    }
    if (socialMedia.linkedinUrl.isNotEmpty()) {
      SocialMediaCard(
        modifier = Modifier,
        label = stringResource(R.string.linkedIn),
        trailingContent = {
          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = stringResource(R.string.linkedIn),
          )
        },
        link = socialMedia.linkedinUrl,
        onClick = onSocialMediaClicked,
      )
    }
    if (socialMedia.githubUrl.isNotEmpty()) {
      SocialMediaCard(
        modifier = Modifier,
        label = stringResource(R.string.github),
        trailingContent = {
          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = stringResource(R.string.github),
          )
        },
        link = socialMedia.githubUrl,
        onClick = onSocialMediaClicked,
      )
    }
    if (socialMedia.youtubeUrl.isNotEmpty()) {
      SocialMediaCard(
        modifier = Modifier,
        label = stringResource(R.string.youtube),
        trailingContent = {
          Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            contentDescription = stringResource(R.string.youtube),
          )
        },
        link = socialMedia.youtubeUrl,
        onClick = onSocialMediaClicked,
      )
    }
    Spacer(Modifier.height(64.dp))
  }
}

@Composable
fun SocialMediaCard(
  modifier: Modifier = Modifier,
  label: String,
  link: String,
  trailingContent: (@Composable () -> Unit)? = {},
  onClick: (link: String) -> Unit = {},
) {
  Surface(
    modifier = modifier
      .fillMaxWidth()
      .height(72.dp)
      .padding(bottom = 16.dp)
      .clickable(
        onClick = {
          onClick(link)
        },
      ),
    shape = MaterialTheme.shapes.small,
    color = MaterialTheme.colorScheme.background,
    border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.outline),
  ) {
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Text(
        text = label,
        modifier = Modifier.weight(1f),
        style = MaterialTheme.typography.bodyMedium,
      )
      trailingContent?.invoke()
    }
  }
}
