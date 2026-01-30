package com.example.feature.rentCar.domain

import com.example.shared.car.domain.entity.Car
import jakarta.inject.Inject

class GetCarUseCase @Inject constructor(private val rentRepository: RentRepository) :
    suspend (String) -> Car by rentRepository::getCar