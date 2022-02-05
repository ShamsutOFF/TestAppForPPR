package com.example.testappforppr

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(var title: String, var screen: ComposableFun) {
    @ExperimentalFoundationApi
    object PrimeNum : TabItem("Простые числа", { PrimeNumbersScreen() })
    object FibonacciNum : TabItem("Числа Фибоначчи", { FibonacciNumbersScreen() })
}