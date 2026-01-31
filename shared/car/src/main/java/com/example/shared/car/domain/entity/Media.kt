package com.example.shared.car.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Media(
    val url: String,
    val isCover: Boolean,
)