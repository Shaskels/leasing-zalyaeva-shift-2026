package com.example.feature.rentCar.domain

import com.example.shared.rent.domain.Rent
import com.example.shared.rent.domain.RentInfo
import jakarta.inject.Inject

class RentCarUseCase @Inject constructor(private val rentRepository: RentRepository) :
    suspend (RentInfo) -> Rent by rentRepository::rentCar