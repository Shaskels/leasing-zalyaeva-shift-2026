package com.example.feature.carList.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.example.feature.carList.domain.repository.CarRepository
import com.example.shared.car.data.CarService
import com.example.shared.car.data.model.CarResponse
import com.example.shared.car.data.toDomainCar
import com.example.shared.car.domain.entity.Car
import com.example.shared.filter.Filter
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CarRepositoryImpl @Inject constructor(private val carService: CarService): CarRepository {

    companion object{
        private const val PAGE_SIZE = 10
    }

    override fun getCars(query: String, filter: Filter): Flow<PagingData<Car>> {
        val pager: Pager<Int, CarResponse> = Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, enablePlaceholders = false),
            pagingSourceFactory = {
                CarPagingSource(
                    api = carService,
                    filter = filter,
                    query = query,
                )
            }
        )
        return pager.flow.map { pagingSource -> pagingSource.map { it -> it.toDomainCar() } }
    }
}