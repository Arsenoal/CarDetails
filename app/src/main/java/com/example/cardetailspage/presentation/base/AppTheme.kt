package com.example.cardetailspage.presentation.base

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.darkColors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun AppTheme(content: @Composable () -> Unit) {
    val DarkColors = darkColors(
        primary = Color(0xFFA66A53),
        primaryVariant = Color(0xFFDAD9D9),
        secondary = Color(0xFF000000),
        secondaryVariant = Color(0xFF3700B3),
        background = Color(0xFFFFFFFF)
    )
    val LightColors = lightColors(
        primary = Color(0xFFA66A53),
        primaryVariant = Color(0xFFDAD9D9),
        secondary = Color(0xFF000000),
        secondaryVariant = Color(0xFF3700B3),
        background = Color(0xFFFFFFFF)
    )
    MaterialTheme(
        colors = if(isSystemInDarkTheme()) DarkColors else LightColors
    ) {
        content()
    }
}