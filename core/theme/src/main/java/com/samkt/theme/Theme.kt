package com.samkt.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

//private val lightScheme = lightColorScheme(
//    primary = primaryLight,
//    onPrimary = onPrimaryLight,
//    primaryContainer = primaryContainerLight,
//    onPrimaryContainer = onPrimaryContainerLight,
//    secondary = secondaryLight,
//    onSecondary = onSecondaryLight,
//    secondaryContainer = secondaryContainerLight,
//    onSecondaryContainer = onSecondaryContainerLight,
//    tertiary = tertiaryLight,
//    onTertiary = onTertiaryLight,
//    tertiaryContainer = tertiaryContainerLight,
//    onTertiaryContainer = onTertiaryContainerLight,
//    error = errorLight,
//    onError = onErrorLight,
//    errorContainer = errorContainerLight,
//    onErrorContainer = onErrorContainerLight,
//    background = backgroundLight,
//    onBackground = onBackgroundLight,
//    surface = surfaceLight,
//    onSurface = onSurfaceLight,
//    surfaceVariant = surfaceVariantLight,
//    onSurfaceVariant = onSurfaceVariantLight,
//    outline = outlineLight,
//    outlineVariant = outlineVariantLight,
//    scrim = scrimLight,
//    inverseSurface = inverseSurfaceLight,
//    inverseOnSurface = inverseOnSurfaceLight,
//    inversePrimary = inversePrimaryLight,
//    surfaceDim = surfaceDimLight,
//    surfaceBright = surfaceBrightLight,
//    surfaceContainerLowest = surfaceContainerLowestLight,
//    surfaceContainerLow = surfaceContainerLowLight,
//    surfaceContainer = surfaceContainerLight,
//    surfaceContainerHigh = surfaceContainerHighLight,
//    surfaceContainerHighest = surfaceContainerHighestLight,
//)
//
//private val darkScheme = darkColorScheme(
//    primary = primaryDark,
//    onPrimary = onPrimaryDark,
//    primaryContainer = primaryContainerDark,
//    onPrimaryContainer = onPrimaryContainerDark,
//    secondary = secondaryDark,
//    onSecondary = onSecondaryDark,
//    secondaryContainer = secondaryContainerDark,
//    onSecondaryContainer = onSecondaryContainerDark,
//    tertiary = tertiaryDark,
//    onTertiary = onTertiaryDark,
//    tertiaryContainer = tertiaryContainerDark,
//    onTertiaryContainer = onTertiaryContainerDark,
//    error = errorDark,
//    onError = onErrorDark,
//    errorContainer = errorContainerDark,
//    onErrorContainer = onErrorContainerDark,
//    background = backgroundDark,
//    onBackground = onBackgroundDark,
//    surface = surfaceDark,
//    onSurface = onSurfaceDark,
//    surfaceVariant = surfaceVariantDark,
//    onSurfaceVariant = onSurfaceVariantDark,
//    outline = outlineDark,
//    outlineVariant = outlineVariantDark,
//    scrim = scrimDark,
//    inverseSurface = inverseSurfaceDark,
//    inverseOnSurface = inverseOnSurfaceDark,
//    inversePrimary = inversePrimaryDark,
//    surfaceDim = surfaceDimDark,
//    surfaceBright = surfaceBrightDark,
//    surfaceContainerLowest = surfaceContainerLowestDark,
//    surfaceContainerLow = surfaceContainerLowDark,
//    surfaceContainer = surfaceContainerDark,
//    surfaceContainerHigh = surfaceContainerHighDark,
//    surfaceContainerHighest = surfaceContainerHighestDark,
//)

// Define your monochrome palette
private val Black = Color(0xFF000000)
private val White = Color(0xFFFFFFFF)
private val OffWhite = Color(0xFFFAFAFA) // For light backgrounds
private val DarkGray = Color(0xFF1C1C1C)  // For dark surfaces
private val MidGray = Color(0xFF666666)   // For secondary elements
private val LightGray = Color(0xFFF0F0F0)  // For light containers
private val OutlineGray = Color(0xFFBDBDBD) // For outlines/borders

// Light B&W Color Scheme
private val BlackAndWhiteLightScheme = lightColorScheme(
    primary = Black,
    onPrimary = White,
    primaryContainer = LightGray,
    onPrimaryContainer = Black,
    secondary = MidGray,
    onSecondary = White,
    secondaryContainer = LightGray,
    onSecondaryContainer = Black,
    tertiary = MidGray,
    onTertiary = White,
    tertiaryContainer = LightGray,
    onTertiaryContainer = Black,
    error = Black, // Note: Using black for error is not accessible. See notes below.
    onError = White,
    errorContainer = LightGray,
    onErrorContainer = Black,
    background = OffWhite,
    onBackground = Black,
    surface = White,
    onSurface = Black,
    surfaceVariant = LightGray,
    onSurfaceVariant = Black,
    outline = OutlineGray,
)

// Dark B&W Color Scheme
private val BlackAndWhiteDarkScheme = darkColorScheme(
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
    surfaceContainer = Black
)


@Composable
fun PortfolioTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        /*dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }*/

        darkTheme -> BlackAndWhiteDarkScheme
        else -> BlackAndWhiteLightScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}