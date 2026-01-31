package com.example.shared.rent.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RentResponse(
    @SerialName("_id")
    val id: String,
    val carInfo: CarInfoResponse,
    val status: Int,
    val pickupLocation: String,
    val returnLocation: String,
    val startDate: Long,
    val endDate: Long,
    val totalPrice: Int,
    val firstName: String,
    val lastName: String,
    val middleName: String,
    val birthDate: String,
    val email: String,
    val phone: String,
)
