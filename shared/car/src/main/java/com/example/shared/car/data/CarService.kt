package com.example.shared.car.data

import com.example.shared.car.data.model.PagedResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CarService {
    @GET("info")
    suspend fun getCars(
        @Query("search")
        query: String,
        @Query("page")
        page: Int
    ): PagedResponse
}