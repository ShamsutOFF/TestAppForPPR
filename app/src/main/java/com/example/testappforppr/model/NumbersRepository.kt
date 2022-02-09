package com.example.testappforppr.model

interface NumbersRepository {
    suspend fun getPrimeNumbers(page: Int) : List<Int>
    suspend fun getFibonacciNumbers(page: Int) :List<Long>
}