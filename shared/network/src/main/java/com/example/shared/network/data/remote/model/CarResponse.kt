package com.example.shared.network.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class CarResponse(
    val id: String,
    val name: String,
    val brand: String,
    val media: List<MediaResponse>,
    val transmission: String,
    val price: Int,
    val location: String,
    val color: String,
    val bodyType: String,
    val steering: String,
    val rents: List<RentResponse>? = null
)