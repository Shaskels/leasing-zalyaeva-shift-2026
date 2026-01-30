package com.example.feature.rentSuccess.domain

import com.example.shared.rent.domain.Rent
import jakarta.inject.Inject

class GetRentUseCase @Inject constructor(private val rentRepository: RentRepository) :
    suspend (String) -> Rent by rentRepository::getRent