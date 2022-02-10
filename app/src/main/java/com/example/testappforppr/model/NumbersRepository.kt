package com.example.testappforppr.model

import java.math.BigInteger

interface NumbersRepository {
    suspend fun getPrimeNumbers(page: Int) : List<BigInteger>
    suspend fun getFibonacciNumbers(page: Int) :List<BigInteger>
}