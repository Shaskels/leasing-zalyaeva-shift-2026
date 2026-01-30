package com.example.feature.rentSuccess.domain

import com.example.shared.rent.domain.Rent

interface RentRepository {
    suspend fun getRent(rentId: String): Rent
}