package com.example.feature.rentSuccess.di

import com.example.feature.rentSuccess.data.RentRepositoryImpl
import com.example.feature.rentSuccess.domain.RentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RentSuccessModule {

    @Binds
    fun bindRentRepository(rentRepositoryImpl: RentRepositoryImpl): RentRepository
}