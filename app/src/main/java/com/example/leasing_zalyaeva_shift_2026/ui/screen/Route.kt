package com.example.leasing_zalyaeva_shift_2026.ui.screen

import androidx.navigation3.runtime.NavKey
import com.example.shared.filter.Filter
import com.example.shared.rent.domain.Rent
import kotlinx.serialization.Serializable

@Serializable
sealed interface Route : NavKey {

    @Serializable
    data class CarList(val filter: Filter?) : Route

    @Serializable
    data object Orders : Route

    @Serializable
    data object Profile : Route

    @Serializable
    data class CarDetails(val carId: String): Route

    @Serializable
    data class RentCar(val carId: String): Route

    @Serializable
    data class RentSuccess(val rent: Rent): Route

    @Serializable
    data class Filters(val filter: Filter): Route
}