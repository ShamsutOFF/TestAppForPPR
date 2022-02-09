package com.example.testappforppr.model

import java.math.BigInteger

interface NumbersRepository {
    suspend fun getPrimeNumbers(page: Int) : List<Int>
    suspend fun getFibonacciNumbers(page: Int) :List<BigInteger>
}