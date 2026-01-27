package com.example.shared.network.data.remote.model

data class PagedResponse (
    val success: Boolean,
    val reason: String,
    val data: List<CarResponse>,
    val meta:
)