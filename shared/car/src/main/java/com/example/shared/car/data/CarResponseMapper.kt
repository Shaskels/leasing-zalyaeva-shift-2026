package com.example.shared.car.data

import com.example.shared.car.domain.entity.Car
import com.example.shared.car.domain.entity.Media
import com.example.shared.car.domain.entity.Rent
import com.example.shared.network.data.remote.model.CarResponse
import com.example.shared.network.data.remote.model.MediaResponse
import com.example.shared.network.data.remote.model.RentResponse

fun CarResponse.toDomainCar() = Car(
    id = id,
    name = name,
    brand = brand,
    media = media.map { it.toDomainMedia() },
    transmission = transmission,
    price = price,
    location = location,
    color = color,
    bodyType = bodyType,
    steering = steering,
    rents = rents?.map { it.toDomainRent() }
)

fun MediaResponse.toDomainMedia() = Media(
    url = url,
    isCover = isCover
)

fun RentResponse.toDomainRent() = Rent(
    startDate = startDate,
    endDate = endDate
)