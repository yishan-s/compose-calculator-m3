package com.example.compose_calculator_m3.ui.calculator.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorButton(
    symbol: String,
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
    aspectRatio: Float = 1f,
    onClick: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(CircleShape)
            .clickable { onClick() }
            .aspectRatio(aspectRatio)
    ) {
        Surface(
            color = containerColor,
            shape = CircleShape,
            modifier = Modifier.matchParentSize()
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = symbol,
                    fontSize = 36.sp,
                    color = contentColor
                )
            }
        }
    }
}