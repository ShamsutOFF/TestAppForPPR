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
import java.lang.Float
import kotlin.math.sqrt

class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestAppForPPRTheme {
                Surface(color = MaterialTheme.colors.background) {
                    Column() {
                        Row(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(), horizontalArrangement = Arrangement.Center
                        ) {
                            TextButton(onClick = { /*TODO*/ }) {
                                Text(text = "Прочтые числа")
                            }
                            TextButton(onClick = { /*TODO*/ }) {
                                Text(text = "Числа Фибоначи")
                            }
                        }
                        MyPrimeNumbers1()
                    }
                }
            }
        }
    }
}

@ExperimentalFoundationApi
@Composable
private fun MyPrimeNumbers1() {
    var t: ArrayList<Int> = arrayListOf()

    fun isPrime(n: Int): Boolean {
        if (n < 2) return false // Необходимо, так как 1 -- не простое число
        for (m in 2..sqrt(n.toDouble()).toInt()) {
            if (n % m == 0) return false
        }
        return true
    }
for(i in 0..100){
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
private fun MyPrimeNumbers() {
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
private fun MyFiboNumbers() {
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
