package com.example.shared.rent.domain

import kotlinx.serialization.Serializable

@Serializable
data class Rent(
    val id: String,
    val carInfo: CarInfo,
    val status: RentStatus,
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
