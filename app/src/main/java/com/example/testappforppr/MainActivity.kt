package com.example.testappforppr

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.testappforppr.view.FibonacciNumbersScreen
import com.example.testappforppr.view.PrimeNumbersScreen
import com.google.accompanist.pager.*
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val TAG = "@@@ MainActivity"

class MainActivity : ComponentActivity() {

    val mainViewModel: MainViewModel by viewModel()

    @InternalCoroutinesApi
    @ExperimentalMaterialApi
    @ExperimentalPagerApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate() called with: savedInstanceState = $savedInstanceState")
        setContent {
            HorizontalPager(mainViewModel)
        }
    }
}

@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun HorizontalPager(mainViewModel: MainViewModel) {
    val pagerState = rememberPagerState(0)
    val scope = rememberCoroutineScope()
    Column() {
        TabRow(
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions), height = 5.dp, color = Color.Red
                )
            }
        ) {
            val pages = listOf("Проятые числа", "Числа Фибоначи")
            pages.forEachIndexed { index, title ->
                Tab(
                    text = { Text(title) },
                    selected = pagerState.currentPage == index,
                    onClick = { scope.launch { pagerState.scrollToPage(index) }},
                )
            }
        }

        HorizontalPager(2, state = pagerState) { page ->
            when (page) {
                0 -> {
                    PrimeNumbersScreen(mainViewModel.primeNumbers)
                }
                1 -> {
                    FibonacciNumbersScreen(mainViewModel.fibonacciNumbers)
                }
            }
        }
    }


}