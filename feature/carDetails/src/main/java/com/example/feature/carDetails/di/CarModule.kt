package com.example.feature.carDetails.di

import com.example.feature.carDetails.data.CarRepositoryImpl
import com.example.feature.carDetails.domain.CarRepository
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