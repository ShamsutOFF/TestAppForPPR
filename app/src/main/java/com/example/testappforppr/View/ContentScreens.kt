package com.example.testappforppr

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@ExperimentalFoundationApi
@Composable
fun PrimeNumbersScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
//            .background(Color.Red)
            .wrapContentSize(Alignment.Center)
    ) {
        MyPrimeNumbers1()
    }
}

//@ExperimentalFoundationApi
//@Preview(showBackground = true)
//@Composable
//fun PrimeNumbersScreenPreview() {
//    PrimeNumbersScreen()
//}

@Composable
fun FibonacciNumbersScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
//            .background(Color.Red)
            .wrapContentSize(Alignment.Center)
    ) {
        MyFibonacciNumbers()
    }
}