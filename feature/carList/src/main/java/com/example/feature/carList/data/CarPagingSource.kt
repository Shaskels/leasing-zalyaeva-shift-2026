package com.example.feature.carList.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.shared.car.data.CarService
import com.example.shared.car.data.model.CarResponse
import com.example.shared.filter.Filter

class CarPagingSource(
    private val api: CarService,
    private val query: String,
    private val filter: Filter
) :
    PagingSource<Int, CarResponse>() {

    companion object {
        private const val START_PAGE_NUMBER = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CarResponse> {
        return try {
            val res = api.getCars(
                query,
                filter.maxPrice,
                filter.minPrice,
                filter.transmission?.type,
                filter.bodyType?.type,
                filter.brand?.type,
                filter.steering?.type,
                params.key ?: START_PAGE_NUMBER
            )
            LoadResult.Page(
                data = res.data,
                prevKey = null,
                nextKey = if (res.meta.totalPages > res.meta.page) res.meta.page + 1 else null
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