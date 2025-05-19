package com.jayanth.walmartassesment.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jayanth.walmartassesment.domain.usecase.CountriesUseCase

/**
 * Factory class to create instances of [CountryViewModel] with constructor parameters.
 *
 * This is useful when the ViewModel requires dependencies (like [CountriesUseCase])
 * and cannot be instantiated using the default no-argument constructor.
 *
 * @property countriesUseCase The use case instance to be passed into the ViewModel.
 */
class CountryViewModelFactory(private val countriesUseCase: CountriesUseCase):
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CountryViewModel(countriesUseCase) as T
    }
}