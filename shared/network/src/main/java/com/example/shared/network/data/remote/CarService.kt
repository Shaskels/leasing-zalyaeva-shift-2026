package com.example.shared.network.data.remote

import com.example.shared.network.data.remote.model.PagedResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CarService {
    @GET("/info")
    suspend fun getCars(
        @Query("search")
        query: String,
        @Query("page")
        page: Int
    ): PagedResponse
}