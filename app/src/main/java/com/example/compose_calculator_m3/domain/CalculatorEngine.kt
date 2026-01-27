package com.example.compose_calculator_m3.domain

/**
 * Hi! This is step one: the core logic.
 * I've heard this layer knows nothing about Android or Compose,
 * making it the perfect spot for unit testing!
 * */
class CalculatorEngine {
    fun calculate(num1: Double, num2: Double, operation: String): Double {
        return when (operation) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> if (num2 != 0.0) num1 / num2 else Double.NaN
            else -> 0.0
        }
    }
}