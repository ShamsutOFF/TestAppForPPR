package com.example.testappforppr.model

import android.util.Log
import java.math.BigInteger
import kotlin.math.sqrt

private const val TAG = "@@@ NumbersRepositoryImpl"

class NumbersRepositoryImpl : NumbersRepository {
    override suspend fun getPrimeNumbers(page: Int): List<Int> {
        val b: Int = page * 20
        val a: Int = b - 19

        val list: ArrayList<Int> = arrayListOf()

        fun isPrime(n: Int): Boolean {
            if (n < 2) return false // Необходимо, так как 1 -- не простое число
            for (m in 2..sqrt(n.toDouble()).toInt()) {
                if (n % m == 0) return false
            }
            return true
        }
        for (i in a..b) {
            if (isPrime(i)) {
                list.add(i)
            }
        }
        return list
    }
    var b1: BigInteger = BigInteger.valueOf(1)
    var b2: BigInteger = BigInteger.valueOf(2)

    override suspend fun getFibonacciNumbers(page: Int): List<BigInteger> {

        val b: Int = page * 20
        val a: Int = b - 19

        val list: ArrayList<BigInteger> = arrayListOf()
        fun nextFibo(): BigInteger {
            var sum = b1 + b2
            b1 = b2
            b2 = sum
            return sum
        }
        for (i in a..b) {
            list.add(nextFibo())
        }
        return list
    }
}