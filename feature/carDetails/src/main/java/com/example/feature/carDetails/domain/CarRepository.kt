package com.example.feature.carDetails.domain

import com.example.shared.car.domain.entity.Car

interface CarRepository {
    suspend fun getCar(carId: String): Car
}