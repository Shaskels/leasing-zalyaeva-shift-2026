package com.example.shared.car.data.model

import kotlinx.serialization.Serializable

@Serializable
data class MetaResponse (
    val total: Int,
    val page: Int,
    val limit: Int,
    val totalPages: Int,
)