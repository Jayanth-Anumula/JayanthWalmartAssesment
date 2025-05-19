package com.jayanth.walmartassesment.data.remote

import com.jayanth.walmartassesment.commons.Constants
import com.jayanth.walmartassesment.data.entities.Countries
import retrofit2.Response
import retrofit2.http.GET

/**
 * Retrofit service interface to define API endpoints related to countries.
 */
interface CountriesApiService {

    /**
     * Fetches the list of countries from the remote source.
     *
     * @return [Response] wrapping a list of [Countries] objects.
     */

    @GET(Constants.END_POINT)
    suspend fun getCountries():Response<List<Countries>>
}