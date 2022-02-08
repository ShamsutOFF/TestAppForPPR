package com.example.testappforppr.View

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import com.example.testappforppr.FibonacciNumbersScreen
import com.example.testappforppr.PrimeNumbersScreen

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(var title: String, var screen: ComposableFun) {
    @ExperimentalFoundationApi
    object PrimeNum : TabItem("Простые числа", { PrimeNumbersScreen() })
    object FibonacciNum : TabItem("Числа Фибоначчи", { FibonacciNumbersScreen() })
}