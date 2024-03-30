package com.strone.presentation.ui.theme

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF2A7EFA),
    onPrimary = Color(0xFFFFFFFF),
    primaryContainer = Color(0xFFE3E8FE),
    onPrimaryContainer = Color(0xFF234EF8),

    secondary = Color(0xFF00398D),
    onSecondary = Color(0xFFFFFFFF),
    secondaryContainer = Color(0xFFF8FAFF),
    onSecondaryContainer = Color(0xFF191F28),

    tertiary = Color(0xFF009688),
    onTertiary = Color(0xFFFFFFFF),
    tertiaryContainer = Color(0xFF00BCD4),
    onTertiaryContainer = Color(0xFF191F28),

    error = Color(0xFFFF3324),
    onError = Color(0xFFFFFFFF),
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),

    surface = Color(0xFFFFFFFF),
    surfaceContainer = Color(0xFFFFFFFF),
    onSurface = Color(0xFF191F26),
    onSurfaceVariant = Color(0xFF323D4C),

    inverseSurface = Color(0xFF191F26),
    inverseOnSurface = Color(0xFFFFFFFF),
    inversePrimary = Color(0xFF2A7EFA),

    background = Color(0xFFF3F4F6),
    outlineVariant = Color(0xFFF3F4F6),
    outline = Color(0xFFF3F4F6),
)

@Composable
fun BitwinTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
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