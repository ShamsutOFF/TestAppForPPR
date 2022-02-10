package com.example.testappforppr.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.Flow
import java.math.BigInteger

@ExperimentalFoundationApi
@Composable
fun PrimeNumbersScreen(
    primeNumbers: Flow<PagingData<BigInteger>>,
    cellsCheckedState: MutableState<Boolean>,
) {
    val lazyNumbersItems: LazyPagingItems<BigInteger> = primeNumbers.collectAsLazyPagingItems()

    val numberOfColumns = if (cellsCheckedState.value) 3
    else 2
    LazyVerticalGrid(cells = GridCells.Fixed(numberOfColumns)) {
        items(count = lazyNumbersItems.itemCount) { index ->
            lazyNumbersItems[index]?.let {
                TakeCardItem(numberOfColumns, index, it)
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
fun FibonacciNumbersScreen(
    fibonacciNumbers: Flow<PagingData<BigInteger>>,
    cellsCheckedState: MutableState<Boolean>,
) {
    val lazyNumbersItems: LazyPagingItems<BigInteger> = fibonacciNumbers.collectAsLazyPagingItems()
    val numberOfColumns = if (cellsCheckedState.value) 3
    else 2
    LazyVerticalGrid(cells = GridCells.Fixed(numberOfColumns)) {
        items(count = lazyNumbersItems.itemCount) { index ->
            lazyNumbersItems[index]?.let {
                TakeCardItem(numberOfColumns, index, it)
            }
        }
    }
}

@Composable
fun TakeCardItem(numberOfColumns: Int, index: Int, it: BigInteger) {
    val cellColor1 = Color.LightGray
    val cellColor2 = Color.White
    if (numberOfColumns == 2) {
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
    } else {
        when (index % 2) {
            0 -> {
                NumberCardItem(number = it, cardColor = cellColor1)
            }
            1 -> {
                NumberCardItem(number = it, cardColor = cellColor2)
            }
        }
    }
}
