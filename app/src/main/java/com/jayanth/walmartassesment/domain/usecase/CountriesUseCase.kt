package com.jayanth.walmartassesment.domain.usecase

import com.jayanth.walmartassesment.commons.ApiResponse
import com.jayanth.walmartassesment.domain.irepository.ICountryRepository
import com.jayanth.walmartassesment.domain.data.CountryDomain
import kotlinx.coroutines.flow.Flow


/**
 * Use case class responsible for executing the business logic to fetch countries.
 *
 * This acts as a bridge between the UI (ViewModel) and the [ICountryRepository],
 * encapsulating the retrieval of country data.
 *
 * @property repository The repository implementation that handles data fetching.
 */

class CountriesUseCase(
    private val repository: ICountryRepository
) {
    /**
     * Operator function that invokes the use case to fetch countries.
     *
     * @return A [Flow] emitting [ApiResponse] with loading, success, or error states
     *         containing a list of [CountryDomain] models.
     */
    operator fun invoke(): Flow<ApiResponse<List<CountryDomain>>> {
        return repository.getCountries()
    }
}
