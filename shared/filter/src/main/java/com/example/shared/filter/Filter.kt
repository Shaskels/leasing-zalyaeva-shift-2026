package com.example.shared.filter

import com.example.shared.car.domain.entity.BodyType
import com.example.shared.car.domain.entity.Brand
import com.example.shared.car.domain.entity.Color
import com.example.shared.car.domain.entity.Steering
import com.example.shared.car.domain.entity.Transmission
import kotlinx.serialization.Serializable

@Serializable
data class Filter(
    val minPrice: Int = 3000,
    val maxPrice: Int = 10000,
    val transmission: Transmission? = null,
    val steering: Steering? = null,
    val bodyType: BodyType? = null,
    val brand: Brand? = null,
    val color: Color? = null
)