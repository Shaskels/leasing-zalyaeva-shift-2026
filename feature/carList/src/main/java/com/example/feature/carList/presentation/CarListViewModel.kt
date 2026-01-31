package com.example.feature.carList.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.feature.carList.domain.GetCarsUseCase
import com.example.shared.filter.Filter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class CarListViewModel @Inject constructor(
    private val getCarsUseCase: GetCarsUseCase
) : ViewModel() {

    private val _filter = MutableStateFlow(Filter())
    val filter = _filter.asStateFlow()
    private val _query = MutableStateFlow("")
    val query = _query.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val cars = combine(
        flow = _filter,
        flow2 = _query.debounce(600)
    ) { filter, query ->
        filter to query
    }.flatMapLatest { pair -> getCarsUseCase(pair.second, pair.first).cachedIn(viewModelScope) }

    fun search(query: String) {
        _query.value = query
    }

    fun setFilters(filter: Filter) {
        _filter.value = filter
    }
}