package com.example.feature.carDetails.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature.carDetails.domain.GetCarUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = CarDetailsViewModel.CarDetailsViewModelFactory::class)
class CarDetailsViewModel @AssistedInject constructor(
    private val getCarUseCase: GetCarUseCase,
    @Assisted private val carId: String
) : ViewModel() {

    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.Loading)
    val screenState = _screenState.asStateFlow()

    val coroutineExceptionHandler = CoroutineExceptionHandler { context, throwable ->
        _screenState.value = ScreenState.Error
    }

    fun loadCar() {
        viewModelScope.launch(coroutineExceptionHandler) {
            _screenState.value = ScreenState.Content( getCarUseCase(carId))
        }
    }

    @AssistedFactory
    interface CarDetailsViewModelFactory {
        fun create(postId: String): CarDetailsViewModel
    }
}