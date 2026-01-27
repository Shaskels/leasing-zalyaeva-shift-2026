package com.example.feature.carList.domain

import androidx.paging.PagingData
import com.example.shared.car.domain.entity.Car
import com.example.shared.car.domain.repository.CarRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetCarsUseCase @Inject constructor(private val carRepository: CarRepository) :
    suspend (String) -> Flow<PagingData<Car>> by carRepository::getCars