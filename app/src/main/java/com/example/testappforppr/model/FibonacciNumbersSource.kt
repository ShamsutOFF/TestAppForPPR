package com.example.testappforppr.model

import androidx.paging.PagingSource
import androidx.paging.PagingState

class FibonacciNumbersSource(private val numbersRepository: NumbersRepository) : PagingSource<Int, Long>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Long> {
        return try {
            val page = params.key ?: 1
            val numbersResponse = numbersRepository.getFibonacciNumbers(page = page)
            LoadResult.Page(
                data = numbersResponse,
                prevKey = if (page == 1) null
                else{page -1},
                nextKey = page.plus(1)
            )
        } catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Long>): Int? {
        return null
    }
}