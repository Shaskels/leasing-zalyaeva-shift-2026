package com.example.shared.car.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.shared.car.domain.entity.Car
import com.example.shared.car.domain.repository.CarRepository
import com.example.shared.network.data.remote.CarService
import com.example.shared.network.data.remote.model.CarResponse
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CarRepositoryImpl @Inject constructor(private val carService: CarService): CarRepository {

    companion object{
        private const val PAGE_SIZE = 10
    }

    override suspend fun getCars(query: String): Flow<PagingData<Car>> {
        val pager: Pager<Int, CarResponse> = Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = {
                CarPagingSource(
                    loadData = { key ->
                        carService.getCars(query, key)
                    }
                )
            }
        )
        return pager.flow.map { pagingSource -> pagingSource.map { it -> it.toDomainCar() } }
    }
}