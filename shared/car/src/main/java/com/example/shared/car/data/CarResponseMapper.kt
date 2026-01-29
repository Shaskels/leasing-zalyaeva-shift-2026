package com.example.shared.car.data

import com.example.shared.car.data.model.CarResponse
import com.example.shared.car.data.model.MediaResponse
import com.example.shared.car.data.model.RentResponse
import com.example.shared.car.domain.entity.BodyType
import com.example.shared.car.domain.entity.Brand
import com.example.shared.car.domain.entity.Car
import com.example.shared.car.domain.entity.Color
import com.example.shared.car.domain.entity.Media
import com.example.shared.car.domain.entity.Rent
import com.example.shared.car.domain.entity.Steering
import com.example.shared.car.domain.entity.Transmission

fun CarResponse.toDomainCar() =
    Car(
        id = id,
        name = name,
        brand = this.brand.convertBrand(),
        media = media.map { it.toDomainMedia() },
        transmission = this.transmission.convertTransmission(),
        price = price,
        location = location,
        color = this.color.convertColor(),
        bodyType = this.bodyType.convertBodyType(),
        steering = this.steering.convertSteering(),
        rents = rents?.map { it.toDomainRent() }
    )


fun String.convertSteering(): Steering {
    return when (this) {
        Steering.LEFT.type -> Steering.LEFT
        Steering.RIGHT.type -> Steering.RIGHT
        else -> throw Exception("Invalid Car")
    }
}

fun String.convertBodyType(): BodyType {
    return when (this) {
        BodyType.SEDAN.type -> BodyType.SEDAN
        BodyType.SUV.type -> BodyType.SUV
        BodyType.COUPE.type -> BodyType.COUPE
        BodyType.HATCHBACK.type -> BodyType.HATCHBACK
        BodyType.CABRIOLET.type -> BodyType.CABRIOLET
        else -> throw Exception("Invalid Car")
    }
}

fun String.convertColor(): Color {
    return when (this) {
        Color.RED.type -> Color.RED
        Color.BLACK.type -> Color.BLACK
        Color.WHITE.type -> Color.WHITE
        Color.SILVER.type -> Color.SILVER
        Color.BLUE.type -> Color.BLUE
        Color.GREY.type -> Color.GREY
        Color.ORANGE.type -> Color.ORANGE
        else -> throw Exception("Invalid Car")
    }
}

fun String.convertTransmission(): Transmission {
    return when (this) {
        Transmission.AUTOMATIC.type -> Transmission.AUTOMATIC
        Transmission.MANUAL.type -> Transmission.MANUAL
        else -> throw Exception("Invalid Car")
    }
}

fun String.convertBrand(): Brand {
    return when (this) {
        Brand.HAVAL.type -> Brand.HAVAL
        Brand.HYUNDAI.type -> Brand.HYUNDAI
        Brand.VOLKSWAGEN.type -> Brand.VOLKSWAGEN
        Brand.KIA.type -> Brand.KIA
        Brand.GEELY.type -> Brand.GEELY
        Brand.MERCEDES.type -> Brand.MERCEDES
        Brand.GARDEN_CAR.type -> Brand.GARDEN_CAR
        Brand.GROCERY_CART.type -> Brand.GROCERY_CART
        Brand.HAIER.type -> Brand.HAIER
        Brand.INVALID.type -> Brand.INVALID
        else -> throw Exception("Invalid Car")
    }
}

fun MediaResponse.toDomainMedia() = Media(
    url = url,
    isCover = isCover
)

fun RentResponse.toDomainRent() = Rent(
    startDate = startDate,
    endDate = endDate
)