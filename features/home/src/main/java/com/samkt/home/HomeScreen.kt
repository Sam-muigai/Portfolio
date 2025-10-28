package com.samkt.home

import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.samkt.domain.models.UserInformation
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(
  homeScreenViewModel: HomeScreenViewModel = koinViewModel(),
  onSettingsClicked: () -> Unit,
  onProjectsClick: () -> Unit,
  onAboutMeClick: () -> Unit,
  onContactClick: () -> Unit,
) {
  val homeScreenUiState =
    homeScreenViewModel.homeScreenUiState.collectAsStateWithLifecycle().value
  val context = LocalContext.current
  HomeScreenContent(
    homeScreenUiState = homeScreenUiState,
    onSettingsClicked = {
      Toast.makeText(context, "Coming soon", Toast.LENGTH_SHORT).show()
    },
    onProjectsClick = onProjectsClick,
    onAboutMeClick = onAboutMeClick,
    onContactClick = onContactClick,
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenContent(
  modifier: Modifier = Modifier,
  homeScreenUiState: HomeScreenUiState,
  onSettingsClicked: () -> Unit = {},
  onProjectsClick: () -> Unit = {},
  onAboutMeClick: () -> Unit = {},
  onContactClick: () -> Unit = {},
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
            stringResource(R.string.portfolio),
            style = MaterialTheme.typography.bodyLarge.copy(
              fontWeight = FontWeight.Bold,
            ),
          )
        },
        actions = {
          IconButton(
            onClick = onSettingsClicked,
          ) {
            Icon(
              imageVector = Icons.Outlined.Settings,
              contentDescription = null,
            )
          }
        },
      )
    },
  ) { paddingValues ->
    AnimatedContent(
      modifier = Modifier.padding(paddingValues),
      targetState = homeScreenUiState,
    ) { homeScreenUiState ->
      when (homeScreenUiState) {
        is HomeScreenUiState.Error -> {
          HomeScreenErrorScreen(
            errorMessage = homeScreenUiState.message,
          )
        }

        HomeScreenUiState.Loading -> {
          HomeScreenLoadingScreen()
        }

        is HomeScreenUiState.Success -> {
          HomeScreenContent(
            userInformation = homeScreenUiState.userInformation,
            onContactClick = onContactClick,
            onProjectsClick = onProjectsClick,
            onAboutMeClick = onAboutMeClick,
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
    contentAlignment = Alignment.Center,
  ) {
    CircularProgressIndicator(
      strokeWidth = 1.5.dp,
    )
  }
}

@Composable
fun HomeScreenErrorScreen(
  modifier: Modifier = Modifier,
  errorMessage: String,
) {
  Box(
    modifier = modifier
      .fillMaxSize(),
    contentAlignment = Alignment.Center,
  ) {
    Text(
      errorMessage,
      fontWeight = FontWeight.Bold,
    )
  }
}

@Composable
fun HomeScreenContent(
  modifier: Modifier = Modifier,
  userInformation: UserInformation,
  onProjectsClick: () -> Unit,
  onAboutMeClick: () -> Unit,
  onContactClick: () -> Unit,
) {
  Column(
    modifier = modifier
      .fillMaxSize()
      .padding(horizontal = 16.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.spacedBy(8.dp),
  ) {
    AsyncImage(
      modifier = Modifier
        .size(96.dp)
        .border(1.5.dp, MaterialTheme.colorScheme.onBackground, CircleShape)
        .clip(
          CircleShape,
        ),
      model = userInformation.profileImage,
      contentDescription = null,
      contentScale = ContentScale.Crop,
    )
    Text(
      userInformation.name,
      style = MaterialTheme.typography.titleLarge.copy(
        fontWeight = FontWeight.Bold,
      ),
    )
    Text(
      userInformation.role,
      style = MaterialTheme.typography.bodyLarge.copy(
        fontWeight = FontWeight.Light,
      ),
    )
    Text(
      userInformation.country,
      style = MaterialTheme.typography.bodyLarge.copy(
        fontWeight = FontWeight.Light,
      ),
    )
    Spacer(modifier = Modifier.height(16.dp))
    Row(
      modifier = Modifier.fillMaxWidth(),
    ) {
      Text(
        stringResource(R.string.quick_access),
        fontWeight = FontWeight.Bold,
      )
    }
    QuickAccessCard(
      icon = R.drawable.ic_outline_work,
      label = stringResource(R.string.projects),
      onClick = onProjectsClick,
    )
    QuickAccessCard(
      icon = R.drawable.ic_outline_person,
      label = stringResource(R.string.about_me),
      onClick = onAboutMeClick,
    )
    QuickAccessCard(
      icon = R.drawable.ic_outline_email,
      label = stringResource(R.string.contact_me),
      onClick = onContactClick,
    )
  }
}

@Composable
fun QuickAccessCard(
  modifier: Modifier = Modifier,
  label: String,
  @DrawableRes icon: Int,
  onClick: () -> Unit = {},
) {
  Surface(
    modifier = modifier
      .fillMaxWidth()
      .padding(bottom = 8.dp)
      .clickable(onClick = onClick),
    color = MaterialTheme.colorScheme.background,
    shape = MaterialTheme.shapes.small,
    border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.outline),
  ) {
    Row(
      modifier = Modifier.padding(16.dp),
      verticalAlignment = Alignment.CenterVertically,
    ) {
      Surface(
        modifier = Modifier,
        color = MaterialTheme.colorScheme.surfaceContainer,
        shape = MaterialTheme.shapes.small,
      ) {
        Box(
          modifier = Modifier
            .size(40.dp)
            .padding(8.dp),
          contentAlignment = Alignment.Center,
        ) {
          Icon(
            painter = painterResource(icon),
            contentDescription = label,
          )
        }
      }
      Spacer(modifier = Modifier.width(16.dp))
      Text(
        label,
        fontWeight = FontWeight.Bold,
      )
    }
  }
}
