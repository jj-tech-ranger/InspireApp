package com.inspirewear.android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = NavyBlue,
    onPrimary = White,
    primaryContainer = NavyBlue,
    onPrimaryContainer = PastelBlue,
    
    secondary = PastelBlue,
    onSecondary = DarkNavy,
    secondaryContainer = PastelBlue,
    onSecondaryContainer = DarkNavy,
    
    tertiary = PastelBlue,
    onTertiary = DarkNavy,
    
    error = ErrorRed,
    onError = White,
    errorContainer = ErrorRed,
    onErrorContainer = White,
    
    background = DarkNavy,
    onBackground = White,
    
    surface = CardBackground,
    onSurface = White,
    surfaceVariant = DarkGrey,
    onSurfaceVariant = LightGrey,
    
    outline = BorderColor,
    outlineVariant = MediumGrey,
    
    scrim = Color.Black,
    inverseSurface = White,
    inverseOnSurface = DarkNavy,
    inversePrimary = NavyBlue,
    
    surfaceTint = PastelBlue
)

@Composable
fun InspireWearTheme(
    darkTheme: Boolean = true, // Always use dark theme
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
