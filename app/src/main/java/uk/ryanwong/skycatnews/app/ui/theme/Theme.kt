/*
 * Copyright (c) 2022. Ryan Wong (hello@ryanwong.co.uk)
 */

package uk.ryanwong.skycatnews.app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = SkyBrandingDarkRed,
    onPrimary = Color.White,
    primaryVariant = SkyBrandingLightRed,
    secondary = Teal200,
    background = SkyBrandingDarkGrey,
    surface = Color.Black,
)

private val LightColorPalette = lightColors(
    primary = SkyBrandingDarkRed,
    onPrimary = Color.White,
    primaryVariant = SkyBrandingLightRed,
    secondary = Purple700,
    background = GreyE8,
    surface = SkyGreyBackground,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun SkyCatNewsTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
