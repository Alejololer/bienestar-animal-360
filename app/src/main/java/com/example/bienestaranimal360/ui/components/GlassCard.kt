package com.example.bienestaranimal360.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun GlassCard(
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val isDark = isSystemInDarkTheme()

    // Mimicking .glass-card css
    // background: rgba(255, 255, 255, 0.7);
    // dark: background: rgba(30, 41, 59, 0.7);
    val containerColor = if (isDark) {
        Color(0xFF1E293B).copy(alpha = 0.7f)
    } else {
        Color.White.copy(alpha = 0.7f)
    }

    val borderColor = if (isDark) {
        Color(0xFFFFFFFF).copy(alpha = 0.05f)
    } else {
        Color(0xFFFFFFFF).copy(alpha = 0.6f)
    }

    Card(
        modifier = modifier.then(
             if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier
        ),
        shape = RoundedCornerShape(24.dp), // .rounded-[2rem] is approx 32px
        colors = CardDefaults.cardColors(
            containerColor = containerColor
        ),
        border = BorderStroke(1.dp, borderColor),
        content = content
    )
}
