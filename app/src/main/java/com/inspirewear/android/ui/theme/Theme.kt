package com.inspirewear.android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = PrimaryColorDark,
    secondary = SecondaryColorDark,
    background = BodyColorDark,
    surface = ContainerColorDark,
    onPrimary = TitleColorDark,
    onSecondary = TitleColorDark,
    onBackground = TextColorDark,
    onSurface = TextColorDark
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryColorLight,
    secondary = SecondaryColorLight,
    background = BodyColorLight,
    surface = ContainerColorLight,
    onPrimary = TitleColorLight,
    onSecondary = TitleColorLight,
    onBackground = TextColorLight,
    onSurface = TextColorLight
)

@Composable
fun InspireWearTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}