package com.example.shared.rent.domain

import com.example.shared.rent.R

enum class RentStatus(val type: Int, val stringId: Int) {
    CREATED(0, R.string.created),
    DONE(1, R.string.done)
}