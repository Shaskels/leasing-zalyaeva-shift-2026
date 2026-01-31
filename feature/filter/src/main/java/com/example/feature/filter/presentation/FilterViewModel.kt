package com.example.feature.filter.presentation

import androidx.lifecycle.ViewModel
import com.example.shared.car.domain.entity.BodyType
import com.example.shared.car.domain.entity.Brand
import com.example.shared.car.domain.entity.Color
import com.example.shared.car.domain.entity.Steering
import com.example.shared.car.domain.entity.Transmission
import com.example.shared.filter.Filter
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class FilterViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(Filter())
    val state = _state.asStateFlow()

    fun setFilters(filter: Filter) {
        _state.value = filter
    }

    fun setMinPrice(price: Int) {
        _state.update {
            it.copy(
                minPrice = price
            )
        }
    }

    fun setTransmission(transmission: Transmission?) {
        _state.update {
            it.copy(
                transmission = transmission
            )
        }
    }

    fun setSteering(steering: Steering?) {
        _state.update {
            it.copy(
                steering = steering
            )
        }
    }


    fun setBodyType(bodyType: BodyType?) {
        _state.update {
            it.copy(
                bodyType = bodyType
            )
        }
    }

    fun setBrand(brand: Brand) {
        _state.update {
            it.copy(
                brand = brand
            )
        }
    }

    fun setColor(color: Color) {
        _state.update {
            it.copy(
                color = color
            )
        }
    }
}