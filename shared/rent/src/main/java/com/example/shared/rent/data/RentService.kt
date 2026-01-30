package com.example.shared.rent.data

import com.example.shared.rent.data.model.RentInfoRequest
import com.example.shared.rent.data.model.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface RentService {

    @POST("rent")
    suspend fun postRent(@Body rentInfoRequest: RentInfoRequest): Response
}