package com.example.compose_calculator_m3.ui.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.compose_calculator_m3.ui.calculator.components.CalculatorButton

@Composable
fun CalculatorScreen() {
    val viewModel = viewModel<CalculatorViewModel>()
    val state = viewModel.state

    val buttonSpacing = 8.dp

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.BottomCenter),
            verticalArrangement = Arrangement.spacedBy(buttonSpacing)
        ) {
            // display area
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                contentAlignment = Alignment.BottomEnd
            ) {
                Text(
                    text = state.displayValue,
                    textAlign = TextAlign.End,
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Light,
                    fontSize = 80.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    lineHeight = 80.sp,
                    maxLines = 2
                )
            }

            // keyboard area
            // Row 1: AC, Del, /, *
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(
                    symbol = "AC",
                    modifier = Modifier.background(Color.Transparent).weight(1f),
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    onClick = { viewModel.onAction(CalculatorAction.Clear) }
                )
                CalculatorButton(
                    symbol = "⌫",
                    modifier = Modifier.background(Color.Transparent).weight(1f),
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    contentColor = MaterialTheme.colorScheme.onSecondaryContainer,
                    onClick = { viewModel.onAction(CalculatorAction.Delete) }
                )

                CalculatorButton(
                    symbol = "/",
                    modifier = Modifier.background(Color.Transparent).weight(1f),
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer, // 運算子色
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    onClick = { viewModel.onAction(CalculatorAction.Operation("/")) }
                )
                CalculatorButton(
                    symbol = "*",
                    modifier = Modifier.background(Color.Transparent).weight(1f),
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    onClick = { viewModel.onAction(CalculatorAction.Operation("*")) }
                )
            }

            // Row 2: 7, 8, 9, -
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(symbol = "7", modifier = Modifier.weight(1f), onClick = { viewModel.onAction(CalculatorAction.Number(7)) })
                CalculatorButton(symbol = "8", modifier = Modifier.weight(1f), onClick = { viewModel.onAction(CalculatorAction.Number(8)) })
                CalculatorButton(symbol = "9", modifier = Modifier.weight(1f), onClick = { viewModel.onAction(CalculatorAction.Number(9)) })
                CalculatorButton(
                    symbol = "-",
                    modifier = Modifier.weight(1f),
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    onClick = { viewModel.onAction(CalculatorAction.Operation("-")) }
                )
            }

            // Row 3: 4, 5, 6, +
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(symbol = "4", modifier = Modifier.weight(1f), onClick = { viewModel.onAction(CalculatorAction.Number(4)) })
                CalculatorButton(symbol = "5", modifier = Modifier.weight(1f), onClick = { viewModel.onAction(CalculatorAction.Number(5)) })
                CalculatorButton(symbol = "6", modifier = Modifier.weight(1f), onClick = { viewModel.onAction(CalculatorAction.Number(6)) })
                CalculatorButton(
                    symbol = "+",
                    modifier = Modifier.weight(1f),
                    containerColor = MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = MaterialTheme.colorScheme.onTertiaryContainer,
                    onClick = { viewModel.onAction(CalculatorAction.Operation("+")) }
                )
            }

            // Row 4: 1, 2, 3, =
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                CalculatorButton(symbol = "1", modifier = Modifier.weight(1f), onClick = { viewModel.onAction(CalculatorAction.Number(1)) })
                CalculatorButton(symbol = "2", modifier = Modifier.weight(1f), onClick = { viewModel.onAction(CalculatorAction.Number(2)) })
                CalculatorButton(symbol = "3", modifier = Modifier.weight(1f), onClick = { viewModel.onAction(CalculatorAction.Number(3)) })
                CalculatorButton(
                    symbol = "=",
                    modifier = Modifier.weight(1f),
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary,
                    onClick = { viewModel.onAction(CalculatorAction.Calculate) }
                )
            }

            // Row 5: 0, .
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(buttonSpacing)
            ) {
                // 0 button
                CalculatorButton(
                    symbol = "0",
                    modifier = Modifier.weight(2f),
                    aspectRatio = 2f,
                    onClick = { viewModel.onAction(CalculatorAction.Number(0)) }
                )

                // dot
                CalculatorButton(
                    symbol = ".",
                    modifier = Modifier.weight(1f),
                    onClick = { viewModel.onAction(CalculatorAction.Decimal) }
                )

                // fill the space
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}