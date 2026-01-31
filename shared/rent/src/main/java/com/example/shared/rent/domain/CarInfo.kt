package com.example.shared.rent.domain

import com.example.shared.car.domain.entity.BodyType
import com.example.shared.car.domain.entity.Brand
import com.example.shared.car.domain.entity.Color
import com.example.shared.car.domain.entity.Media
import com.example.shared.car.domain.entity.Steering
import com.example.shared.car.domain.entity.Transmission
import kotlinx.serialization.Serializable

@Serializable
data class CarInfo(
    val id: String,
    val name: String,
    val brand: Brand,
    val img: List<Media>,
    val transmission: Transmission,
    val price: Int,
    val location: String,
    val color: Color,
    val bodyType: BodyType,
    val steering: Steering
)
