package com.example.shared.rent.data.model

import kotlinx.serialization.Serializable

@Serializable
data class Response(
    val success: Boolean,
    val reason: String,
    val rentResponse: RentResponse
)
