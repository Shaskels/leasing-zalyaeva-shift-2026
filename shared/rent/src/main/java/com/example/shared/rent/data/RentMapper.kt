package com.example.shared.rent.data

import com.example.shared.car.data.convertBodyType
import com.example.shared.car.data.convertBrand
import com.example.shared.car.data.convertColor
import com.example.shared.car.data.convertSteering
import com.example.shared.car.data.convertTransmission
import com.example.shared.rent.data.model.CarInfoResponse
import com.example.shared.rent.data.model.RentInfoRequest
import com.example.shared.rent.data.model.RentResponse
import com.example.shared.rent.domain.CarInfo
import com.example.shared.rent.domain.Rent
import com.example.shared.rent.domain.RentInfo

fun RentResponse.toDomainRent(): Rent = Rent(
    id = id,
    carInfo = carInfo.toDomainCarInfo(),
    status = status,
    pickupLocation = pickupLocation,
    returnLocation = returnLocation,
    startDate = startDate,
    endDate = endDate,
    totalPrice = totalPrice,
    firstName = firstName,
    lastName = lastName,
    middleName = middleName,
    birthDate = birthDate,
    email = email,
    phone = phone,
    comment = comment
)

fun CarInfoResponse.toDomainCarInfo(): CarInfo = CarInfo(
    id = id,
    name = name,
    brand = this.brand.convertBrand(),
    img = img,
    transmission = this.brand.convertTransmission(),
    price = price,
    location = location,
    color = this.color.convertColor(),
    bodyType = this.bodyType.convertBodyType(),
    steering = this.steering.convertSteering()
)

fun RentInfo.toModel(): RentInfoRequest = RentInfoRequest(
    birthDate = birthDate,
    carId = carId,
    email = email,
    endDate = endDate,
    firstName = firstName,
    lastName = lastName,
    phone = phone,
    pickupLocation = pickupLocation,
    returnLocation = returnLocation,
    startDate = startDate,
    comment = comment,
    middleName = middleName
)