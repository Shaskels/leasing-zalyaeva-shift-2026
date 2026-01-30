package com.example.feature.rentCar.presentation

import com.example.shared.car.domain.entity.Car
import com.example.shared.rent.domain.Rent
import com.example.shared.rent.domain.RentInfo

sealed interface ScreenState {
    data object Initial : ScreenState
    data class Success(val rent: Rent) : ScreenState

    data object Error: ScreenState
    data class Content(
        val stage: RentStage,
        val rentInfo: RentInfo
    ) : ScreenState
}

sealed interface RentStage {
    data class CarRent(val validationState: ValidationState): RentStage
    data class ClientData(val validationState: ValidationState): RentStage
    data class DataCheck(val state: DataCheckState) : RentStage
    data object Loading: RentStage
}

sealed interface DataCheckState {
    data object Loading: DataCheckState
    data class Content(val car: Car): DataCheckState
}

enum class ValidationState {
    FIRST_NAME_INVALID,
    LAST_NAME_INVALID,
    EMAIL_INVALID,
    PHONE_INVALID,
    BIRTH_DATE_INVALID,
    PICKUP_LOCATION_INVALID,
    RETURN_LOCATION_INVALID,
    DATES_INVALID,
    VALID
}