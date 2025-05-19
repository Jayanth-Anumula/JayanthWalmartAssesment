package com.jayanth.walmartassesment.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jayanth.walmartassesment.commons.ApiResponse
import com.jayanth.walmartassesment.domain.data.CountryDomain
import com.jayanth.walmartassesment.domain.usecase.CountriesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

/**
 * ViewModel responsible for managing and exposing country data to the UI layer.
 *
 * This ViewModel uses [CountriesUseCase] to fetch a list of countries and exposes the data
 * as a [StateFlow] of [ApiResponse] to be observed by the UI.
 *
 * @property countriesUseCase The use case used to fetch country data.
 */
class CountryViewModel(
    private val countriesUseCase: CountriesUseCase
) : ViewModel() {

    // Backing property for exposing the current API state to the UI
    private val _countryState = MutableStateFlow<ApiResponse<List<CountryDomain>>>(ApiResponse.Loading)

    // Publicly exposed immutable [StateFlow] of the country API response.
    val countryState: StateFlow<ApiResponse<List<CountryDomain>>> = _countryState.asStateFlow()

    // Initial fetch when ViewModel is created
    init {
        fetchCountries()
    }

    /**
     * Launches a coroutine to collect country data from the use case
     * and updates the state flow accordingly.
     */
    private fun fetchCountries() {
        viewModelScope.launch {
            countriesUseCase().collect { response ->
                _countryState.value = response
            }
        }
    }
}
