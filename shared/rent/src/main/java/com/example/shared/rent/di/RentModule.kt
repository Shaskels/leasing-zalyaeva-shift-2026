package com.example.shared.rent.di

import com.example.shared.rent.data.RentService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class RentModule {
    @Provides
    @Singleton
    fun provideCarService(retrofit: Retrofit): RentService {
        return retrofit.create(RentService::class.java)
    }
}