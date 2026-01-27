package com.example.compose_calculator_m3.ui.calculator

sealed interface CalculatorAction {
    data class Number(val number: Int) : CalculatorAction    // 使用者按了 0-9
    data class Operation(val operation: String) : CalculatorAction // 使用者按了 +, -, *, /
    data object Clear : CalculatorAction                     // 使用者按了 AC (全清)
    data object Delete : CalculatorAction                    // 使用者按了倒退鍵 (刪除一個字)
    data object Decimal : CalculatorAction                   // 使用者按了小數點
    data object Calculate : CalculatorAction                 // 使用者按了 =
}