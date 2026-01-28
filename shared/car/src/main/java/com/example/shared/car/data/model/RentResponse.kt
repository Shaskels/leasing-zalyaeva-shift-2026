package com.example.shared.car.data.model

import kotlinx.serialization.Serializable

@Serializable
data class RentResponse (
    val startDate: Long,
    val endDate: Long,
)