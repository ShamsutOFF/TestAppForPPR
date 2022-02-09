package com.example.testappforppr.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun NumberCardItem(number: Long, cardColor: Color) {
    Card(modifier = Modifier
        .padding(4.dp)
        .fillMaxSize(),
        elevation = 10.dp,
        backgroundColor = cardColor) {
        Box(contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize().height(100.dp)) {
            Text(text = number.toString(),
                style = MaterialTheme.typography.h3,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}