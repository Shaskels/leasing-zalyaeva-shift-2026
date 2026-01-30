package com.example.feature.rentCar.presentation

import com.example.shared.car.domain.entity.Car
import com.example.shared.rent.domain.RentInfo

sealed interface ScreenState {
    data object Initial : ScreenState
    data class Success(val rentId: String) : ScreenState

    data object Error: ScreenState
    data class Content(
        val stage: RentStage,
        val rentInfo: RentInfo
    ) : ScreenState
}

sealed interface RentStage {
    data object CarRent: RentStage
    data object ClientData: RentStage
    data class DataCheck(val state: DataCheckState) : RentStage
    data object Loading: RentStage
}

sealed interface DataCheckState {
    data object Loading: DataCheckState
    data class Content(val car: Car): DataCheckState
}