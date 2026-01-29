package com.example.shared.car.domain.entity

import com.example.shared.car.R

enum class BodyType(val type: String, val stringId: Int) {
    SEDAN("sedan", R.string.sedan),
    SUV("suv", R.string.suv),
    COUPE("coupe", R.string.coupe),
    HATCHBACK("hatchback", R.string.hatchback),
    CABRIOLET("cabriolet", R.string.cabriolet)
}