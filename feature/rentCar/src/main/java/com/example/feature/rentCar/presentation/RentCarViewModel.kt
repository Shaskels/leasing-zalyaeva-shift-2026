package com.example.feature.rentCar.presentation

import android.util.Log
import androidx.core.text.isDigitsOnly
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
import java.time.DateTimeException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
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
        Log.d("Error", throwable.message.toString() + throwable.stackTraceToString())
    }

    fun firstStage() {
        _screenState.value = Content(
            RentStage.CarRent(ValidationState.VALID),
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
                is RentStage.CarRent -> {
                    val valid = validateFirstStage()
                    if (valid == ValidationState.VALID) {
                        currentState.copy(
                            stage = ClientData(valid),
                        )
                    } else {
                        currentState.copy(
                            stage = RentStage.CarRent(valid),
                        )
                    }
                }

                is ClientData -> {
                    val valid = validateAllFields()
                    if (valid == ValidationState.VALID) {
                        currentState.copy(
                            stage = RentStage.DataCheck(DataCheckState.Loading),
                        )
                    } else {
                        currentState.copy(
                            stage = ClientData(valid),
                        )
                    }
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
                        stage = RentStage.CarRent(validateFirstStage()),
                    )

                is ClientData -> {
                    currentState.copy(
                        stage = ClientData(validateAllFields()),
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

                val rent = (_screenState.value as Content).rentInfo
                val res = rentCarUseCase(
                    rent.copy(
                        birthDate = birthDateToISODate(rent.birthDate) ?: "",
                        phone = "+7" + rent.phone
                    )
                )
                _screenState.value = ScreenState.Success(res)
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

    private fun validateFirstStage(): ValidationState {
        return if (_screenState.value is Content) {
            val rent = (_screenState.value as Content).rentInfo

            when {
                rent.startDate == null || rent.endDate == null -> ValidationState.DATES_INVALID
                rent.pickupLocation == "" -> ValidationState.PICKUP_LOCATION_INVALID
                rent.returnLocation == "" -> ValidationState.RETURN_LOCATION_INVALID
                else -> ValidationState.VALID
            }
        } else ValidationState.DATES_INVALID
    }

    private fun validateAllFields(): ValidationState {
        return if (_screenState.value is Content) {
            val rent = (_screenState.value as Content).rentInfo

            when {
                rent.startDate == null || rent.endDate == null -> ValidationState.DATES_INVALID
                rent.pickupLocation == "" -> ValidationState.PICKUP_LOCATION_INVALID
                rent.returnLocation == "" -> ValidationState.RETURN_LOCATION_INVALID
                rent.lastName == "" -> ValidationState.LAST_NAME_INVALID
                rent.firstName == "" -> ValidationState.FIRST_NAME_INVALID
                birthDateToISODate(rent.birthDate) == null -> ValidationState.BIRTH_DATE_INVALID
                rent.phone.length < 10 -> ValidationState.PHONE_INVALID
                !android.util.Patterns.EMAIL_ADDRESS.matcher(rent.email)
                    .matches() -> ValidationState.EMAIL_INVALID

                else -> ValidationState.VALID
            }
        } else ValidationState.DATES_INVALID
    }

    fun birthDateToISODate(birthDate: String): String? {
        if (birthDate.length < 8 || !birthDate.isDigitsOnly()) return null

        val day = birthDate.substring(0, 2).toInt()
        val month = birthDate.substring(2, 4).toInt()
        val year = birthDate.substring(4, 8).toInt()
        var date: LocalDate? = null
        try {
            date = LocalDate.of(year, month, day)
        } catch (e: DateTimeException) {
            Log.d("DateTimeError", e.message.toString())
        }

        return date?.format(DateTimeFormatter.ISO_DATE)
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