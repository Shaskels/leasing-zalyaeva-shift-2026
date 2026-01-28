package com.example.feature.carDetails.presentation

import com.example.shared.car.domain.entity.Car

sealed interface ScreenState {
    data object Loading: ScreenState
    data object Error: ScreenState
    data class Content(val car: Car): ScreenState
}