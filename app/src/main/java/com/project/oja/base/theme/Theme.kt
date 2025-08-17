package com.project.oja.base.theme


import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val lightScheme = lightColorScheme(
    primary = OjaColor.primaryLight,
    onPrimary = OjaColor.onPrimaryLight,
    primaryContainer = OjaColor.primaryContainerLight,
    onPrimaryContainer = OjaColor.onPrimaryContainerLight,
    secondary = OjaColor.secondaryLight,
    onSecondary = OjaColor.onSecondaryLight,
    secondaryContainer = OjaColor.secondaryContainerLight,
    onSecondaryContainer = OjaColor.onSecondaryContainerLight,
    tertiary = OjaColor.tertiaryLight,
    onTertiary = OjaColor.onTertiaryLight,
    tertiaryContainer = OjaColor.tertiaryContainerLight,
    onTertiaryContainer = OjaColor.onTertiaryContainerLight,
    error = OjaColor.errorLight,
    onError = OjaColor.onErrorLight,
    errorContainer = OjaColor.errorContainerLight,
    onErrorContainer = OjaColor.onErrorContainerLight,
    background = OjaColor.backgroundLight,
    onBackground = OjaColor.onBackgroundLight,
    surface = OjaColor.surfaceLight,
    onSurface = OjaColor.onSurfaceLight,
    surfaceVariant = OjaColor.surfaceVariantLight,
    onSurfaceVariant = OjaColor.onSurfaceVariantLight,
    outline = OjaColor.outlineLight,
    outlineVariant = OjaColor.outlineVariantLight,
    scrim = OjaColor.scrimLight,
    inverseSurface = OjaColor.inverseSurfaceLight,
    inverseOnSurface = OjaColor.inverseOnSurfaceLight,
    inversePrimary = OjaColor.inversePrimaryLight,
    surfaceDim = OjaColor.surfaceDimLight,
    surfaceBright = OjaColor.surfaceBrightLight,
    surfaceContainerLowest = OjaColor.surfaceContainerLowestLight,
    surfaceContainerLow = OjaColor.surfaceContainerLowLight,
    surfaceContainer = OjaColor.surfaceContainerLight,
    surfaceContainerHigh = OjaColor.surfaceContainerHighLight,
    surfaceContainerHighest = OjaColor.surfaceContainerHighestLight,
)

private val darkScheme = darkColorScheme(
    primary = OjaColor.primaryDark,
    onPrimary = OjaColor.onPrimaryDark,
    primaryContainer = OjaColor.primaryContainerDark,
    onPrimaryContainer = OjaColor.onPrimaryContainerDark,
    secondary = OjaColor.secondaryDark,
    onSecondary = OjaColor.onSecondaryDark,
    secondaryContainer = OjaColor.secondaryContainerDark,
    onSecondaryContainer = OjaColor.onSecondaryContainerDark,
    tertiary = OjaColor.tertiaryDark,
    onTertiary = OjaColor.onTertiaryDark,
    tertiaryContainer = OjaColor.tertiaryContainerDark,
    onTertiaryContainer = OjaColor.onTertiaryContainerDark,
    error = OjaColor.errorDark,
    onError = OjaColor.onErrorDark,
    errorContainer = OjaColor.errorContainerDark,
    onErrorContainer = OjaColor.onErrorContainerDark,
    background = OjaColor.backgroundDark,
    onBackground = OjaColor.onBackgroundDark,
    surface = OjaColor.surfaceDark,
    onSurface = OjaColor.onSurfaceDark,
    surfaceVariant = OjaColor.surfaceVariantDark,
    onSurfaceVariant = OjaColor.onSurfaceVariantDark,
    outline = OjaColor.outlineDark,
    outlineVariant = OjaColor.outlineVariantDark,
    scrim = OjaColor.scrimDark,
    inverseSurface = OjaColor.inverseSurfaceDark,
    inverseOnSurface = OjaColor.inverseOnSurfaceDark,
    inversePrimary = OjaColor.inversePrimaryDark,
    surfaceDim = OjaColor.surfaceDimDark,
    surfaceBright = OjaColor.surfaceBrightDark,
    surfaceContainerLowest = OjaColor.surfaceContainerLowestDark,
    surfaceContainerLow = OjaColor.surfaceContainerLowDark,
    surfaceContainer = OjaColor.surfaceContainerDark,
    surfaceContainerHigh = OjaColor.surfaceContainerHighDark,
    surfaceContainerHighest = OjaColor.surfaceContainerHighestDark,
)

@Composable
fun OJATheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = false,
    content: @Composable() () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> darkScheme
        else -> lightScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = OjaTypography,
        content = content
    )
}

