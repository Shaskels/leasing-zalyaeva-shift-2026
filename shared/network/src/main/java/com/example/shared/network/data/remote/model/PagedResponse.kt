package com.example.shared.network.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class PagedResponse (
    val success: Boolean,
    val reason: String,
    val data: List<CarResponse>,
    val meta: MetaResponse
)