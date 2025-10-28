package com.samkt.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val Black = Color(0xFF000000)
private val White = Color(0xFFFFFFFF)
private val OffWhite = Color(0xFFFAFAFA)
private val DarkGray = Color(0xFF1C1C1C)
private val MidGray = Color(0xFF666666)
private val LightGray = Color(0xFFF0F0F0)
private val OutlineGray = Color(0xFFBDBDBD)

private val darkScheme = darkColorScheme(
  primary = White,
  onPrimary = Black,
  primaryContainer = DarkGray,
  onPrimaryContainer = White,
  secondary = LightGray,
  onSecondary = Black,
  secondaryContainer = DarkGray,
  onSecondaryContainer = White,
  tertiary = LightGray,
  onTertiary = Black,
  tertiaryContainer = DarkGray,
  onTertiaryContainer = White,
  error = Color.Red,
  onError = Black,
  errorContainer = DarkGray,
  onErrorContainer = White,
  background = Black,
  onBackground = White,
  surface = DarkGray,
  onSurface = White,
  surfaceVariant = DarkGray,
  onSurfaceVariant = LightGray,
  outline = MidGray,
  surfaceContainer = Black,
)

@Composable
fun PortfolioTheme(
  content: @Composable () -> Unit,
) {
  MaterialTheme(
    colorScheme = darkScheme,
    typography = Typography,
    content = content,
  )
}
