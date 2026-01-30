package com.example.feature.rentCar.data

import com.example.feature.rentCar.domain.RentRepository
import com.example.shared.car.data.CarService
import com.example.shared.car.data.toDomainCar
import com.example.shared.car.domain.entity.Car
import com.example.shared.rent.data.RentService
import com.example.shared.rent.data.toDomainRent
import com.example.shared.rent.data.toModel
import com.example.shared.rent.domain.Rent
import com.example.shared.rent.domain.RentInfo
import javax.inject.Inject

class RentRepositoryImpl @Inject constructor(private val rentService: RentService, private val carService: CarService) :
    RentRepository {

    override suspend fun rentCar(rentInfo: RentInfo): Rent {
        return rentService.postRent(rentInfo.toModel()).rentResponse.toDomainRent()
    }

    override suspend fun getCar(carId: String): Car {
        return carService.getCar(carId).data.toDomainCar()
    }
}