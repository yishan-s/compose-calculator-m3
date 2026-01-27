package com.example.compose_calculator_m3.ui.calculator

data class CalculatorUiState(
    val number1: String = "",        // 第一個輸入的數字
    val number2: String = "",        // 第二個輸入的數字
    val operation: String? = null,   // 目前選擇的運算子 (可能還沒選，所以是 null)

    // 這是真正要顯示在螢幕上的字串
    // 我們把它獨立出來，這樣 ViewModel 可以決定要顯示 "Error" 還是數字
    val displayValue: String = "0"
)