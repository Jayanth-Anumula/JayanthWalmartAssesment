package com.jayanth.walmartassesment.data.repositories

import com.jayanth.walmartassesment.commons.ApiResponse
import com.jayanth.walmartassesment.data.mappers.toDomain
import com.jayanth.walmartassesment.data.remote.CountriesApiService
import com.jayanth.walmartassesment.domain.irepository.ICountryRepository
import com.jayanth.walmartassesment.domain.data.CountryDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

/**
 * Implementation of [ICountryRepository] that fetches data from a remote API
 * using [CountriesApiService] and emits results wrapped in [ApiResponse].
 *
 * @property countriesApiService The Retrofit service used to fetch country data.
 */
class CountryRepositoryImpl(
    private val countriesApiService: CountriesApiService
) : ICountryRepository {

    /**
     * Fetches the list of countries from the API, maps them to [CountryDomain],
     * and emits the result wrapped in [ApiResponse].
     *
     * @return A [Flow] emitting the state of the API call.
     */
    override fun getCountries(): Flow<ApiResponse<List<CountryDomain>>> = flow {
        emit(ApiResponse.Loading)

        val response = countriesApiService.getCountries()

        if (response.isSuccessful) {
            val body = response.body()?.map { it.toDomain() }.orEmpty()
            emit(ApiResponse.Success(body))
        } else {
            emit(ApiResponse.Error("Error: ${response.code()} ${response.message()}"))
        }
    }.catch { e ->
        emit(ApiResponse.Error("Exception: ${e.localizedMessage ?: "Unknown error"}"))
    }
}
