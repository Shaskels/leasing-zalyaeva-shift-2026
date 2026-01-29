package com.example.feature.rentCar.di

import com.example.feature.rentCar.data.RentRepositoryImpl
import com.example.feature.rentCar.domain.RentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RentCarModule {

    @Binds
    fun bindRentRepository(rentRepositoryImpl: RentRepositoryImpl): RentRepository
}