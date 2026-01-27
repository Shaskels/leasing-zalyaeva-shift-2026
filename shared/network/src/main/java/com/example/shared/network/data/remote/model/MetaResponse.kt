package com.example.shared.network.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class MetaResponse (
    val total: Int,
    val page: Int,
    val limit: Int,
    val totalPages: Int,
)