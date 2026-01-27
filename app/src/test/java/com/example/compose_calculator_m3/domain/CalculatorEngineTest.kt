package com.example.compose_calculator_m3.domain

import org.junit.Assert.assertEquals
import org.junit.Test

class CalculatorEngineTest {
    private val engine = CalculatorEngine()

    @Test
    fun `test addition - standard case`() {
        val result = engine.calculate(10.0, 5.0, "+")
        assertEquals(15.0, result, 0.001) // 第三個參數是誤差容許值
    }

    @Test
    fun `test substraction - standard case`() {
        val result = engine.calculate(10.0, 5.0, "-")
        assertEquals(5.0, result, 0.001)
    }

    @Test
    fun `test division by zero - should return NaN`() {
        val result = engine.calculate(10.0, 0.0, "/")
        assert(result.isNaN())
    }

    @Test
    fun `test multiplication - decimal points`() {
        val result = engine.calculate(0.1, 0.2, "*")
        assertEquals(0.02, result, 0.001)
    }
}