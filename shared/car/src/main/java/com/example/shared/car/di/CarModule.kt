package com.example.shared.car.di

import com.example.shared.car.data.CarRepositoryImpl
import com.example.shared.car.domain.repository.CarRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CarModule {

    @Binds
    fun bindCarRepository(carRepositoryImpl: CarRepositoryImpl): CarRepository
}