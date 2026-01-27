package com.example.shared.network.data.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class MediaResponse(
    val url: String,
    val isCover: Boolean,
)