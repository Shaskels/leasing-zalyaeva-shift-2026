package com.example.shared.car.domain.entity

import com.example.shared.car.R

enum class Transmission(val type: String, val stringId: Int) {
    AUTOMATIC("automatic", R.string.automatic),
    MANUAL("manual", R.string.manual)
}