package com.example.shared.car.domain.repository

import androidx.paging.PagingData
import com.example.shared.car.domain.entity.Car
import kotlinx.coroutines.flow.Flow

interface CarRepository {
    suspend fun getCars(query: String): Flow<PagingData<Car>>
}