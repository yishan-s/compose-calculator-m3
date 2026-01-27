package com.example.compose_calculator_m3.ui.calculator

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.compose_calculator_m3.domain.CalculatorEngine

class CalculatorViewModel : ViewModel() {

    // 1. dependency injection
    private val engine = CalculatorEngine()

    // 2. state mgt.
    var state by mutableStateOf(CalculatorUiState())
        private set

    // 3. intent handling
    fun onAction(action: CalculatorAction) {
        when (action) {
            is CalculatorAction.Number -> enterNumber(action.number)
            is CalculatorAction.Operation -> enterOperation(action.operation)
            is CalculatorAction.Decimal -> enterDecimal()
            is CalculatorAction.Clear -> state = CalculatorUiState() // 重置狀態
            is CalculatorAction.Delete -> performDeletion()
            is CalculatorAction.Calculate -> performCalculation()
        }
    }

    /* private Logic: defining how to use CalculatorEngine */
    private fun enterNumber(number: Int) {
        val MAX_LENGTH = 8 // 工程師細節：限制長度避免 UI 跑版

        if (state.operation == null) {
            // Case 1: 還沒選運算子，正在輸入第一個數字
            if (state.number1.length >= MAX_LENGTH) return

            val newNumber = state.number1 + number
            state = state.copy(
                number1 = newNumber,
                displayValue = newNumber // 記得：UI 顯示的是 displayValue
            )
        } else {
            // Case 2: 已經選了運算子，正在輸入第二個數字
            if (state.number2.length >= MAX_LENGTH) return

            val newNumber = state.number2 + number
            state = state.copy(
                number2 = newNumber,
                displayValue = newNumber // 這時候畫面要顯示第二個數字了
            )
        }
    }

    private fun enterOperation(op: String) {
        // 防呆：如果第一個數字是空的，不能按加減乘除 (除非你要支援負數，但我們先簡單點)
        if (state.number1.isNotBlank()) {
            state = state.copy(operation = op)
        }
    }

    private fun enterDecimal() {
        if (state.operation == null) {
            // 正在輸入第一個數
            if (!state.number1.contains(".") && state.number1.isNotBlank()) {
                val newNumber = state.number1 + "."
                state = state.copy(
                    number1 = newNumber,
                    displayValue = newNumber
                )
            }
        } else {
            // 正在輸入第二個數
            if (!state.number2.contains(".") && state.number2.isNotBlank()) {
                val newNumber = state.number2 + "."
                state = state.copy(
                    number2 = newNumber,
                    displayValue = newNumber
                )
            }
        }
    }

    private fun performDeletion() {
        when {
            // 情況 A: 正在輸入第二個數字 -> 刪除第二個數字的尾碼
            state.operation != null && state.number2.isNotBlank() -> {
                val newNumber = state.number2.dropLast(1)
                state = state.copy(
                    number2 = newNumber,
                    displayValue = newNumber
                )
            }
            // 情況 B: 只有運算子，還沒打第二個數字 -> 刪除運算子，退回輸入第一個數字的狀態
            state.operation != null && state.number2.isBlank() -> {
                state = state.copy(
                    operation = null,
                    displayValue = state.number1 // 畫面切換回第一個數字
                )
            }
            // 情況 C: 正在輸入第一個數字 -> 刪除第一個數字的尾碼
            state.number1.isNotBlank() -> {
                val newNumber = state.number1.dropLast(1)
                state = state.copy(
                    number1 = newNumber,
                    displayValue = if (newNumber.isEmpty()) "0" else newNumber
                )
            }
        }
    }

    private fun performCalculation() {
        // 這裡就是 ViewModel 與 Engine 的黃金交叉點！
        val num1 = state.number1.toDoubleOrNull()
        val num2 = state.number2.toDoubleOrNull()
        val op = state.operation

        if (num1 != null && num2 != null && op != null) {
            val result = engine.calculate(num1, num2, op)

            // 更新 UI State 顯示結果
            state = state.copy(
                displayValue = result.toString().removeSuffix(".0"), // 去掉整數後面的 .0
                number1 = result.toString().removeSuffix(".0"),      // 把結果變成下一次運算的基底
                number2 = "",
                operation = null
            )
        }
    }
}