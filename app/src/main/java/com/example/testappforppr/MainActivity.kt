package com.example.testappforppr

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import com.example.testappforppr.view.FibonacciNumbersScreen
import com.example.testappforppr.view.PrimeNumbersScreen
import com.google.accompanist.pager.*
import kotlinx.coroutines.InternalCoroutinesApi
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