package com.example.feature.carList.di

import com.example.feature.carList.data.CarRepositoryImpl
import com.example.feature.carList.domain.repository.CarRepository
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