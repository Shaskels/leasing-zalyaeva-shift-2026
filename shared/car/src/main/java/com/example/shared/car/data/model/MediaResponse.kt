package com.example.shared.car.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MediaResponse(
    val url: String,
    val isCover: Boolean,
)