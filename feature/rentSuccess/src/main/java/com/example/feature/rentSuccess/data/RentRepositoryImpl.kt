package com.example.feature.rentSuccess.data

import com.example.feature.rentSuccess.domain.RentRepository
import com.example.shared.rent.data.RentService
import com.example.shared.rent.data.toDomainRent
import com.example.shared.rent.domain.Rent
import javax.inject.Inject

class RentRepositoryImpl @Inject constructor(private val rentService: RentService) : RentRepository {
    override suspend fun getRent(rentId: String): Rent {
        return rentService.getRent(rentId).toDomainRent()
    }

}