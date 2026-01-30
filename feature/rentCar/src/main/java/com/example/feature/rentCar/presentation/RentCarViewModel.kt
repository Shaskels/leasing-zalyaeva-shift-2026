package com.example.feature.rentCar.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature.rentCar.domain.GetCarUseCase
import com.example.feature.rentCar.domain.RentCarUseCase
import com.example.feature.rentCar.presentation.RentStage.ClientData
import com.example.feature.rentCar.presentation.ScreenState.Content
import com.example.shared.rent.domain.RentInfo
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

@HiltViewModel(assistedFactory = RentCarViewModel.RentCarViewModelFactory::class)
class RentCarViewModel @AssistedInject constructor(
    private val rentCarUseCase: RentCarUseCase,
    private val getCarUseCase: GetCarUseCase,
    @Assisted private val carId: String,
) : ViewModel() {

    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.Initial)
    val screenState = _screenState.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { context, throwable ->
        _screenState.value = ScreenState.Error
    }

    fun firstStage() {
        _screenState.value = Content(
            RentStage.CarRent,
            RentInfo(
                birthDate = "",
                carId = carId,
                email = "",
                endDate = null,
                firstName = "",
                lastName = "",
                phone = "",
                pickupLocation = "",
                returnLocation = "",
                startDate = null,
                comment = null,
                middleName = null
            )
        )
    }

    fun nextStage() {
        _screenState.updateState<Content> { currentState ->
            when (currentState.stage) {
                is RentStage.CarRent ->
                    currentState.copy(
                        stage = ClientData,
                    )

                is ClientData -> {
                    currentState.copy(
                        stage = RentStage.DataCheck(DataCheckState.Loading),
                    )
                }

                is RentStage.DataCheck -> {
                    currentState
                }

                RentStage.Loading -> currentState
            }
        }
    }

    fun goToStage(stage: RentStage) {
        _screenState.updateState<Content> { currentState ->
            when (stage) {
                is RentStage.CarRent ->
                    currentState.copy(
                        stage = RentStage.CarRent,
                    )

                is ClientData -> {
                    currentState.copy(
                        stage = ClientData,
                    )
                }

                is RentStage.DataCheck -> {
                    currentState.copy(
                        stage = RentStage.DataCheck(DataCheckState.Loading),
                    )
                }

                RentStage.Loading -> currentState
            }
        }
    }

    fun rentCar() {
        viewModelScope.launch(coroutineExceptionHandler) {
            if (_screenState.value is Content) {
                _screenState.updateState<Content> { currentState ->
                    currentState.copy(
                        stage = RentStage.Loading
                    )
                }
                val res = rentCarUseCase(
                    (_screenState.value as Content).rentInfo
                )
                _screenState.value = ScreenState.Success(res.id)
            }
        }
    }

    fun loadCar() {
        viewModelScope.launch {
            _screenState.updateState<Content> { currentState ->
                currentState.copy(
                    stage = RentStage.DataCheck(DataCheckState.Loading),
                )
            }
            val res = getCarUseCase(carId)
            _screenState.updateState<Content> { currentState ->
                currentState.copy(
                    stage = RentStage.DataCheck(DataCheckState.Content(res)),
                )
            }
        }
    }


    fun setDates(startDate: Long?, endDate: Long?) {
        _screenState.updateState<Content> { currentState ->
            currentState.copy(
                rentInfo = currentState.rentInfo.copy(
                    startDate = startDate,
                    endDate = endDate
                )
            )
        }
    }

    fun setPickupLocation(location: String) {
        _screenState.updateState<Content> { currentState ->
            currentState.copy(
                rentInfo = currentState.rentInfo.copy(
                    pickupLocation = location
                )
            )
        }
    }

    fun setReturnLocation(location: String) {
        _screenState.updateState<Content> { currentState ->
            currentState.copy(
                rentInfo = currentState.rentInfo.copy(
                    returnLocation = location
                )
            )
        }
    }

    fun setFirstName(name: String) {
        _screenState.updateState<Content> { currentState ->
            currentState.copy(
                rentInfo = currentState.rentInfo.copy(
                    firstName = name
                )
            )
        }
    }

    fun setLastName(name: String) {
        _screenState.updateState<Content> { currentState ->
            currentState.copy(
                rentInfo = currentState.rentInfo.copy(
                    lastName = name
                )
            )
        }
    }

    fun setMiddleName(name: String) {
        _screenState.updateState<Content> { currentState ->
            currentState.copy(
                rentInfo = currentState.rentInfo.copy(
                    middleName = name
                )
            )
        }
    }

    fun setPhone(phone: String) {
        _screenState.updateState<Content> { currentState ->
            currentState.copy(
                rentInfo = currentState.rentInfo.copy(
                    phone = phone
                )
            )
        }
    }

    fun setBirthDate(date: String) {
        _screenState.updateState<Content> { currentState ->
            currentState.copy(
                rentInfo = currentState.rentInfo.copy(
                    birthDate = date
                )
            )
        }
    }

    fun setEmail(email: String) {
        _screenState.updateState<Content> { currentState ->
            currentState.copy(
                rentInfo = currentState.rentInfo.copy(
                    email = email
                )
            )
        }
    }

    fun setComment(comment: String) {
        _screenState.updateState<Content> { currentState ->
            currentState.copy(
                rentInfo = currentState.rentInfo.copy(
                    comment = comment
                )
            )
        }
    }


    fun getRentDays(start: Long?, end: Long?): Int {
        if (start == null || end == null) return 0

        val range = end - start
        val days = TimeUnit.DAYS.convert(range, TimeUnit.MILLISECONDS)
        return days.toInt()
    }

    fun convertMillisToDate(start: Long?, end: Long?): String {
        if (start == null || end == null) return ""

        val startDate = Date(start)
        val endDate = Date(end)
        val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        return "${formatter.format(startDate)} - ${formatter.format(endDate)}"
    }

    private inline fun <reified T : ScreenState> MutableStateFlow<ScreenState>.updateState(
        block: (T) -> T
    ) {
        if (this.value is T) {
            this.update {
                block(this.value as T)
            }
        }
    }

    @AssistedFactory
    interface RentCarViewModelFactory {
        fun create(carId: String): RentCarViewModel
    }

}