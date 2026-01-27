package com.example.shared.car.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.shared.network.data.remote.model.CarResponse
import com.example.shared.network.data.remote.model.PagedResponse

class CarPagingSource(private val loadData: suspend (Int) -> PagedResponse) :
    PagingSource<Int, CarResponse>() {

    companion object {
        private const val START_PAGE_NUMBER = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CarResponse> {
        return try {
            val res = loadData(
                params.key ?: START_PAGE_NUMBER
            )
            LoadResult.Page(
                data = res.data,
                prevKey = null,
                nextKey = if (res.meta.totalPages != res.meta.page) null else res.meta.page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CarResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
        }
    }
}