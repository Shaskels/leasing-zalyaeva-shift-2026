package com.example.feature.carList.domain.repository

import androidx.paging.PagingData
import com.example.shared.car.domain.entity.Car
import com.example.shared.filter.Filter
import kotlinx.coroutines.flow.Flow

interface CarRepository {
    fun getCars(query: String, filter: Filter): Flow<PagingData<Car>>
}