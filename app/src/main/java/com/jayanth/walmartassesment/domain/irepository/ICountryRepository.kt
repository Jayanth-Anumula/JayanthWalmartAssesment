package com.jayanth.walmartassesment.domain.irepository

import com.jayanth.walmartassesment.commons.ApiResponse
import com.jayanth.walmartassesment.domain.data.CountryDomain
import kotlinx.coroutines.flow.Flow

interface ICountryRepository {
    /**
     * Fetches the list of countries and emits it wrapped in an [ApiResponse] using [Flow].
     *
     * @return A [Flow] emitting [ApiResponse] containing a list of [CountryDomain] or error/loading state.
     */
    fun getCountries(): Flow<ApiResponse<List<CountryDomain>>>
}