package com.example.feature.rentCar.domain

import com.example.shared.rent.domain.Rent
import com.example.shared.rent.domain.RentInfo

interface RentRepository {
    suspend fun rentCar(rentInfo: RentInfo): Rent
}