package com.example.testappforppr.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.Flow

@ExperimentalFoundationApi
@Composable
fun PrimeNumbersScreen(primeNumbers: Flow<PagingData<Int>>) {
    val lazyNumbersItems: LazyPagingItems<Int> = primeNumbers.collectAsLazyPagingItems()
    val cellColor1 = Color.LightGray
    val cellColor2 = Color.White

    LazyVerticalGrid(cells = GridCells.Fixed(2)) {
        items(count = lazyNumbersItems.itemCount) { index ->
            lazyNumbersItems[index]?.let {
                when (index % 4) {
                    0 -> {
                        NumberCardItem(number = it.toLong(), cardColor = cellColor1)
                    }
                    1 -> {
                        NumberCardItem(number = it.toLong(), cardColor = cellColor2)
                    }
                    2 -> {
                        NumberCardItem(number = it.toLong(), cardColor = cellColor2)
                    }
                    3 -> {
                        NumberCardItem(number = it.toLong(), cardColor = cellColor1)
                    }
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun FibonacciNumbersScreen(fibonacciNumbers: Flow<PagingData<Long>>) {
    val lazyNumbersItems: LazyPagingItems<Long> = fibonacciNumbers.collectAsLazyPagingItems()
    val cellColor1 = Color.LightGray
    val cellColor2 = Color.White

    LazyVerticalGrid(cells = GridCells.Fixed(2)) {
        items(count = lazyNumbersItems.itemCount) { index ->
            lazyNumbersItems[index]?.let {
                when (index % 4) {
                    0 -> {
                        NumberCardItem(number = it, cardColor = cellColor1)
                    }
                    1 -> {
                        NumberCardItem(number = it, cardColor = cellColor2)
                    }
                    2 -> {
                        NumberCardItem(number = it, cardColor = cellColor2)
                    }
                    3 -> {
                        NumberCardItem(number = it, cardColor = cellColor1)
                    }
                }
            }
        }
    }
}