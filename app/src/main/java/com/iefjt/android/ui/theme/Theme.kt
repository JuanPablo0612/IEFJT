package com.iefjt.android.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = LightGreen,
    onPrimary = DarkGray,
    primaryContainer = Blue,
    onPrimaryContainer = DarkGray,
    inversePrimary = MediumGreen,
    secondary = Red,
    onSecondary = DarkGray,
    secondaryContainer = DarkGreen,
    onSecondaryContainer = DarkGray,
    tertiary = DarkGray,
    onTertiary = LightGreen,
    tertiaryContainer = Blue,
    onTertiaryContainer = LightGreen,
    background = DarkGray,
    onBackground = White,
    surface = DarkGray,
    onSurface = LightGreen,
    surfaceVariant = DarkGreen,
    onSurfaceVariant = LightGreen,
    surfaceTint = MediumGreen,
    inverseSurface = White,
    inverseOnSurface = DarkGray,
    error = Red,
    onError = DarkGray,
    errorContainer = Red,
    onErrorContainer = DarkGray,
    outline = Blue,
    outlineVariant = DarkGreen,
    scrim = DarkGreen
)

private val LightColorScheme = lightColorScheme(
    primary = Blue,
    onPrimary = White,
    primaryContainer = MediumGreen,
    onPrimaryContainer = White,
    inversePrimary = DarkGreen,
    secondary = Red,
    onSecondary = White,
    secondaryContainer = LightGreen,
    onSecondaryContainer = White,
    tertiary = DarkGray,
    onTertiary = White,
    tertiaryContainer = MediumGreen,
    onTertiaryContainer = White,
    background = White,
    onBackground = DarkGray,
    surface = White,
    onSurface = DarkGray,
    surfaceVariant = LightGreen,
    onSurfaceVariant = White,
    surfaceTint = MediumGreen,
    inverseSurface = DarkGray,
    inverseOnSurface = White,
    error = Red,
    onError = White,
    errorContainer = Red,
    onErrorContainer = White,
    outline = Blue,
    outlineVariant = DarkGreen,
    scrim = DarkGray
)

@Composable
fun IEFJTTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}