package com.example.shared.car.di

import com.example.shared.car.data.CarService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class CarServiceModule {
    @Provides
    @Singleton
    fun provideCarService(retrofit: Retrofit): CarService {
        return retrofit.create(CarService::class.java)
    }
}