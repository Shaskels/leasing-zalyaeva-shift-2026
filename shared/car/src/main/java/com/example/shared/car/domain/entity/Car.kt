package com.example.shared.car.domain.entity

data class Car (
    val id: String,
    val name: String,
    val brand: String,
    val media: List<Media>,
    val transmission: String,
    val price: Int,
    val location: String,
    val color: String,
    val bodyType: String,
    val steering: String,
    val rents: List<Rent>? = null
)