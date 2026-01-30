package com.example.shared.rent.domain

data class Rent(
    val id: String,
    val carInfo: CarInfo,
    val status: String,
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
    val comment: String,
)
