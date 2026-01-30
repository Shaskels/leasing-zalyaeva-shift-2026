package com.example.feature.rentSuccess.presentation

import com.example.shared.rent.domain.Rent

sealed interface ScreenState {
    data object Loading: ScreenState
    data object Error: ScreenState
    data class Content(val rent: Rent): ScreenState
}