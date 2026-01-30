package com.example.feature.rentSuccess.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature.rentSuccess.domain.GetRentUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@HiltViewModel(assistedFactory = RentSuccessViewModel.RentSuccessViewModelFactory::class)
class RentSuccessViewModel @AssistedInject constructor(
    private val getRentUseCase: GetRentUseCase,
    @Assisted private val rentId: String
) : ViewModel() {

    private val _screenState = MutableStateFlow<ScreenState>(ScreenState.Loading)
    val screenState = _screenState.asStateFlow()

    private val coroutineExceptionHandler = CoroutineExceptionHandler { context, throwable ->
        _screenState.value = ScreenState.Error
    }

    fun loadRent() {
        _screenState.value = ScreenState.Loading
        viewModelScope.launch(coroutineExceptionHandler) {
            _screenState.value = ScreenState.Content( getRentUseCase(rentId))
        }
    }

    fun convertMillisToDate(start: Long?, end: Long?): String {
        if (start == null || end == null) return ""

        val startDate = Date(start)
        val endDate = Date(end)
        val formatter = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        return "${formatter.format(startDate)} - ${formatter.format(endDate)}"
    }


    @AssistedFactory
    interface RentSuccessViewModelFactory {
        fun create(rentId: String): RentSuccessViewModel
    }
}