package com.example.shared.rent.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CarInfoResponse(
    val id: String,
    val name: String,
    val brand: String,
    val img: String,
    val transmission: String,
    val price: Int,
    val location: String,
    val color: String,
    val bodyType: String,
    val steering: String
)
