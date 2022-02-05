package com.example.testappforppr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testappforppr.ui.theme.TestAppForPPRTheme
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import java.lang.Float
import kotlin.math.sqrt

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    @ExperimentalPagerApi
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalFoundationApi
@ExperimentalPagerApi
@Composable
fun MainScreen() {
    val tabs = listOf(TabItem.PrimeNum, TabItem.FibonacciNum)
    val pagerState = rememberPagerState(pageCount = tabs.size)
    Scaffold(topBar = {
        TopBar()
    }) {
        Column() {
            Tabs(tabs = tabs, pagerState = pagerState)
            TabsContent(tabs = tabs, pagerState = pagerState)
        }
    }
}

@Composable
fun TopBar() {
    TopAppBar(
        title = {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Test App for PPR from Shamsutov Adel",
                    fontSize = 18.sp,
                    textAlign = TextAlign.Right,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    )
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun Tabs(tabs: List<TabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    TabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.pagerTabIndicatorOffset(
                    pagerState = pagerState,
                    tabPositions = tabPositions
                ), height = 4.dp, color = Color.Red
            )
        }
    ) {
        tabs.forEachIndexed { index, tabItem ->
            LeadingIconTab(
                icon = {},
                text = { Text(text = tabItem.title, fontSize = 18.sp,style = MaterialTheme.typography.body1) },
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                })
        }
    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(state = pagerState) { page ->
        tabs[page].screen()
    }
}


@ExperimentalFoundationApi
@Composable
fun MyPrimeNumbers1() {
    var t: ArrayList<Int> = arrayListOf()

    fun isPrime(n: Int): Boolean {
        if (n < 2) return false // Необходимо, так как 1 -- не простое число
        for (m in 2..sqrt(n.toDouble()).toInt()) {
            if (n % m == 0) return false
        }
        return true
    }
    for (i in 0..100) {
        if (isPrime(i)) t.add(i)
    }
    LazyVerticalGrid(
        cells = GridCells.Fixed(2)
    )
    {
        items(t) {
            if (isPrime(it)) {
                Card(
                    modifier = Modifier
                        .padding(4.dp),
                    backgroundColor = Color.LightGray
                ) {
                    Text(
                        text = it.toString(),
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(24.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun MyPrimeNumbers() {
    var primeNumber = 2

    fun isPrime(n: Int): Boolean {
        if (n < 2) return false // Необходимо, так как 1 -- не простое число
        for (m in 2..sqrt(n.toDouble()).toInt()) {
            if (n % m == 0) return false
        }
        return true
    }

    fun getNextPrimeNumber(): Boolean {
        primeNumber++
        while (!isPrime(primeNumber)) primeNumber++
        return true
    }
    LazyColumn {
        items(Int.MAX_VALUE) {
            if (isPrime(it)) {
                var numOfCell = it
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    items(2) {
                        Card(
                            modifier = Modifier
                                .padding(4.dp)
                                .width(180.dp),
                            backgroundColor = Color.LightGray
                        ) {
//                            getNextPrimeNumber()
                            Text(
                                text = "numOfCell  = $numOfCell , PNum = $primeNumber",
                                fontSize = 24.sp,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.padding(24.dp)
                            )
                            getNextPrimeNumber()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MyFibonacciNumbers() {
    var t1: Long = 0
    var t2: Long = 1

    fun nextFibo() {
        if (t2 >= 0) {
            val sum = t1 + t2
            t1 = t2
            t2 = sum
        } else {
            t1 = 0
            t2 = 1
        }
    }

    LazyColumn {
        items(Float.POSITIVE_INFINITY.toInt()) {
            if (t1 < 0) {
                Text(
                    "Конец! Даже Long больше не вмещает!",
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.padding(10.dp)
                )
            } else {
                Text(
                    "Item: $it, Fib: $t1",
                    style = MaterialTheme.typography.h3,
                    modifier = Modifier.padding(10.dp)
                )
                Divider(color = Color.Black, thickness = 5.dp)
                nextFibo()
            }
        }
    }
}
