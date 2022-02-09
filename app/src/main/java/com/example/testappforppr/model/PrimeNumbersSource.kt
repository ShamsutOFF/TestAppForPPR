package com.example.testappforppr.model

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.testappforppr.model.NumbersRepository

class PrimeNumbersSource(private val numbersRepository: NumbersRepository) : PagingSource<Int, Int>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Int> {
        return try {
            val page = params.key ?: 1
            val numbersResponse = numbersRepository.getPrimeNumbers(page = page)
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

    override fun getRefreshKey(state: PagingState<Int, Int>): Int? {
        return null
    }
}