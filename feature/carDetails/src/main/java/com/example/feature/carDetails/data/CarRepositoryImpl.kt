package com.example.feature.carDetails.data

import com.example.feature.carDetails.domain.CarRepository
import com.example.shared.car.data.CarService
import com.example.shared.car.data.toDomainCar
import com.example.shared.car.domain.entity.Car
import jakarta.inject.Inject

class CarRepositoryImpl @Inject constructor(private val carService: CarService) : CarRepository {
    override suspend fun getCar(carId: String): Car =
        carService.getCar(carId).data.toDomainCar()

}