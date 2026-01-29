package com.example.shared.car.domain.entity

data class Car (
    val id: String,
    val name: String,
    val brand: Brand,
    val media: List<Media>,
    val transmission: Transmission,
    val price: Int,
    val location: String,
    val color: Color,
    val bodyType: BodyType,
    val steering: Steering,
    val rents: List<Rent>? = null
)

fun Car.getCover(): String = media.find { it.isCover }?.url ?: media.first().url