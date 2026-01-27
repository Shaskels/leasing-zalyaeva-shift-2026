package com.example.leasing_zalyaeva_shift_2026.ui.screen

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route : NavKey {

    @Serializable
    data object CarList : Route

    @Serializable
    data object Orders : Route

    @Serializable
    data object Profile : Route
}