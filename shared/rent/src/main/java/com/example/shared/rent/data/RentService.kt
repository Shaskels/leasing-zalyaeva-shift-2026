package com.example.shared.rent.data

import com.example.shared.rent.data.model.RentInfoRequest
import com.example.shared.rent.data.model.RentResponse
import com.example.shared.rent.data.model.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface RentService {

    @POST("rent")
    suspend fun postRent(@Body rentInfoRequest: RentInfoRequest): Response

    @GET("rent/{carRentId}")
    suspend fun getRent(@Path("carRentId") rentId: String): RentResponse
}