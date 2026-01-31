package com.example.shared.rent.data.model

import com.example.shared.car.data.model.MediaResponse
import kotlinx.serialization.Serializable

@Serializable
data class CarInfoResponse(
    val id: String,
    val name: String,
    val brand: String,
    val media: List<MediaResponse>,
    val transmission: String,
    val price: Int,
    val location: String,
    val color: String,
    val bodyType: String,
    val steering: String
)
