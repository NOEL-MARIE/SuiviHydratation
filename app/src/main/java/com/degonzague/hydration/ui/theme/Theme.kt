package com.degonzague.hydration.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView

private val DarkColorScheme = darkColorScheme(
    primary = ElectricTurquoise,
    secondary = SoftTurquoise,
    tertiary = TurquoiseLight,
    background = DeepBackground,
    surface = SurfaceCard,
    onPrimary = DeepBackground,
    onSecondary = DeepBackground,
    onBackground = OnSurfaceWhite,
    onSurface = OnSurfaceWhite
)

private val LightColorScheme = lightColorScheme(
    primary = TurquoiseDark,
    secondary = SoftTurquoise,
    background = OnSurfaceWhite,
    surface = SurfaceCard,
    onPrimary = OnSurfaceWhite,
    onSecondary = DeepBackground,
    onBackground = DeepBackground,
    onSurface = DeepBackground
)

@Composable
fun HydrationTrackerTheme(
    darkTheme: Boolean = true, // Force Dark Theme as requested
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            window.navigationBarColor = colorScheme.background.toArgb()
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
