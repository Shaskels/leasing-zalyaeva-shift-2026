package com.example.feature.carList.domain

import androidx.paging.PagingData
import com.example.feature.carList.domain.repository.CarRepository
import com.example.shared.car.domain.entity.Car
import com.example.shared.filter.Filter
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetCarsUseCase @Inject constructor(private val carRepository: CarRepository) {

    operator fun invoke(query: String, filter: Filter): Flow<PagingData<Car>> {
        return carRepository.getCars(query, filter)
    }
}