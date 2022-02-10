package com.example.testappforppr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.testappforppr.view.FibonacciNumbersScreen
import com.example.testappforppr.view.PrimeNumbersScreen
import com.google.accompanist.pager.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    val mainViewModel: MainViewModel by viewModel()

    @InternalCoroutinesApi
    @ExperimentalMaterialApi
    @ExperimentalPagerApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
    val scaffoldState = rememberScaffoldState()
    val cellsCheckedState = remember { mutableStateOf(false) }

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopTabRow(pagerState, scope)
        },
        bottomBar = {
            BottomAppBar() {
                CellsSwitch(cellsCheckedState)
            }
        },
        content = {
            HorizontalPager(2, state = pagerState) { page ->
                when (page) {
                    0 -> {
                        PrimeNumbersScreen(mainViewModel.primeNumbers, cellsCheckedState)
                    }
                    1 -> {
                        FibonacciNumbersScreen(mainViewModel.fibonacciNumbers, cellsCheckedState)
                    }
                }
            }
        }
    )
}

@ExperimentalPagerApi
@Composable
fun TopTabRow(pagerState: PagerState, scope: CoroutineScope) {
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions),
                height = 5.dp,
                color = Color.Red
            )
        }
    ) {
        val pages = listOf("Проятые числа", "Числа Фибоначи")
        pages.forEachIndexed { index, title ->
            Tab(
                text = { Text(title) },
                selected = pagerState.currentPage == index,
                onClick = { scope.launch { pagerState.scrollToPage(index) } },
            )
        }
    }
}

@Composable
fun CellsSwitch(cellsCheckedState: MutableState<Boolean>) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center) {
        Text(text = "Две колонки",
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis)
        Switch(checked = cellsCheckedState.value,
            onCheckedChange = {
                cellsCheckedState.value = it
            })
        Text(text = "Три колонки",
            style = MaterialTheme.typography.body1,
            overflow = TextOverflow.Ellipsis
        )
    }
}
