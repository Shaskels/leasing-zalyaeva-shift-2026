package com.example.shared.rent.domain

data class RentInfo(
    val birthDate: String,
    val carId: String,
    val email: String,
    val endDate: String,
    val firstName: String,
    val lastName: String,
    val phone: String,
    val pickupLocation: String,
    val returnLocation: String,
    val startDate: String,
    val comment: String?,
    val middleName: String?
)
