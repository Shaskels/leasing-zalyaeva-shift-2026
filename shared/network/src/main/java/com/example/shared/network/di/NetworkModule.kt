package com.example.shared.network.di

import com.example.shared.network.data.remote.CarService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jakarta.inject.Singleton
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideJsonConverter() = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    @Provides
    fun jsonConverterFactory(
        json: Json,
        jsonMediaType: MediaType
    ): retrofit2.Converter.Factory = json.asConverterFactory(jsonMediaType)

    @Provides
    @Singleton
    fun provideRetrofit(factory: retrofit2.Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://shift-intensive.ru/api/cars")
            .addConverterFactory(factory)
            .build()
    }

    @Provides
    @Singleton
    fun provideCarService(retrofit: Retrofit): CarService {
        return retrofit.create(CarService::class.java)
    }
}