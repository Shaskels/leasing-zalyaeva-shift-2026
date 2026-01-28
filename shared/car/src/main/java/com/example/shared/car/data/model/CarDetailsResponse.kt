package com.example.shared.car.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CarDetailsResponse (
    val success: Boolean,
    val reason: String,
    val data: CarResponse,
)