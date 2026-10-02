package com.mih.delivery.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val MihColors = lightColorScheme(
    primary = Color(0xFF005F54), secondary = Color(0xFFFFB000), tertiary = Color(0xFF266E63),
    background = Color(0xFFF8FAF9), surface = Color.White
)
@Composable fun MihTheme(content: @Composable () -> Unit) = MaterialTheme(colorScheme = MihColors, content = content)
