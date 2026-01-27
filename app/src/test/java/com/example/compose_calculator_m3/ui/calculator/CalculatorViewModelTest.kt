package com.example.compose_calculator_m3.ui.calculator

import org.junit.Assert.assertEquals
import org.junit.Test

class CalculatorViewModelTest {

    private val viewModel = CalculatorViewModel()

    @Test
    fun `enterNumber - appends digits correctly`() {
        // 模擬使用者行為：按下 1, 2, 3
        viewModel.onAction(CalculatorAction.Number(1))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Number(3))

        // 驗證狀態：畫面應該要顯示 "123"
        assertEquals("123", viewModel.state.displayValue)
    }

    @Test
    fun `enterDecimal - ignores duplicate decimals`() {
        // 模擬：按下 3 . . 5 (試圖按兩次小數點)
        viewModel.onAction(CalculatorAction.Number(3))
        viewModel.onAction(CalculatorAction.Decimal)
        viewModel.onAction(CalculatorAction.Decimal) // 這個應該要被無視
        viewModel.onAction(CalculatorAction.Number(5))

        // 驗證：畫面應該是 "3.5"，而不是 "3..5"
        assertEquals("3.5", viewModel.state.displayValue)
    }

    @Test
    fun `performCalculation - standard addition`() {
        // 模擬完整的加法流程： 20 + 30 =

        // 1. 輸入 20
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Number(0))

        // 2. 按下 +
        viewModel.onAction(CalculatorAction.Operation("+"))

        // 3. 輸入 30
        viewModel.onAction(CalculatorAction.Number(3))
        viewModel.onAction(CalculatorAction.Number(0))

        // 4. 按下 =
        viewModel.onAction(CalculatorAction.Calculate)

        // 驗證：
        // displayValue 應該是 "50" (因為我們有邏輯去掉 .0)
        // number1 應該也被更新成 "50" (為了讓使用者可以繼續算)
        assertEquals("50", viewModel.state.displayValue)
        assertEquals("50", viewModel.state.number1)
        assertEquals("", viewModel.state.number2) // 第二個數字應該被清空
    }

    @Test
    fun `performDeletion - handles backspace correctly`() {
        // 模擬：輸入 123 -> 按刪除 -> 應該剩 12
        viewModel.onAction(CalculatorAction.Number(1))
        viewModel.onAction(CalculatorAction.Number(2))
        viewModel.onAction(CalculatorAction.Number(3))

        viewModel.onAction(CalculatorAction.Delete)

        assertEquals("12", viewModel.state.displayValue)
    }
}