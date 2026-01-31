package com.example.shared.car.data

import com.example.shared.car.data.model.CarDetailsResponse
import com.example.shared.car.data.model.PagedResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CarService {
    @GET("info")
    suspend fun getCars(
        @Query("search")
        query: String,
        @Query("maxPrice")
        maxPrice: Int,
        @Query("minPrice")
        minPrice: Int,
        @Query("transmission")
        transmission: String? = null,
        @Query("bodyType")
        bodyType: String? = null,
        @Query("brand")
        brand: String? = null,
        @Query("steering")
        steering: String? = null,
        @Query("page")
        page: Int
    ): PagedResponse

    @GET("info/{carId}")
    suspend fun getCar(
        @Path("carId")
        carId: String,
    ): CarDetailsResponse
}