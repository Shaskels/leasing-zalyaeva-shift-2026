package com.example.feature.carDetails.domain

import com.example.shared.car.domain.entity.Car
import jakarta.inject.Inject

class GetCarsUseCase @Inject constructor(private val carRepository: CarRepository) :
    suspend (String) -> Car by carRepository::getCar