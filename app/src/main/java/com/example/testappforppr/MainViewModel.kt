package com.example.testappforppr

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.testappforppr.model.FibonacciNumbersSource
import com.example.testappforppr.model.NumbersRepositoryImpl
import com.example.testappforppr.model.PrimeNumbersSource
import kotlinx.coroutines.flow.Flow

class MainViewModel (numbersRepositoryImpl : NumbersRepositoryImpl) : ViewModel() {
    val primeNumbers: Flow<PagingData<Int>> = Pager(PagingConfig(pageSize = 20)){
        PrimeNumbersSource(numbersRepositoryImpl)
    }.flow
    val fibonacciNumbers: Flow<PagingData<Long>> = Pager(PagingConfig(pageSize = 20)){
        FibonacciNumbersSource(numbersRepositoryImpl)
    }.flow
}