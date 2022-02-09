package com.example.testappforppr.model

import android.util.Log
import kotlin.math.sqrt

private const val TAG = "@@@ NumbersRepositoryImpl"

class NumbersRepositoryImpl : NumbersRepository {
//    var a: Int = 0
//    var b: Int = 200
    override suspend fun getPrimeNumbers(page: Int): List<Int> {
        Log.d(TAG, "getPrimeNumbers() called with: page = $page")
    val b: Int = page*200
    val a: Int = b-199

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
//        a = b
//        b += 200
        return list
    }

    override suspend fun getFibonacciNumbers(page: Int): List<Long> {
        Log.d(TAG, "getFibonacciNumbers() called with: page = $page")
        val b: Int = page*200
        val a: Int = b-199

        val list: ArrayList<Long> = arrayListOf()
        var l1: Long = 1
        var l2: Long = 2

        fun nextFibo(): Long {
            var sum = l1 + l2
            if (sum >= 0) {
                l1 = l2
                l2 = sum
            } else {
                l1 = 1
                l2 = 2
                sum = 3
            }
            return sum
        }
        for (i in a..b) {
            list.add(nextFibo())
        }
//        a = b
//        b += 200
        return list
    }
}