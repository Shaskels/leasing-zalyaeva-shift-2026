package com.example.shared.network.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class RentResponse (
    val startDate: Long,
    val endDate: Long,
)