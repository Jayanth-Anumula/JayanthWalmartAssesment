package com.jayanth.walmartassesment.data.remote

import com.jayanth.walmartassesment.commons.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton object that provides a configured [Retrofit] instance
 * for making network requests to the countries API.
 */
object CountriesApiClient {

    /**
     * Lazy-initialized [Retrofit] instance with logging enabled.
     */
    val retrofit:Retrofit by lazy {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val okHttpClient = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()

        Retrofit.Builder().apply {
            baseUrl(Constants.BASE_URL)
            client(okHttpClient)
            addConverterFactory(GsonConverterFactory.create())
        }.build()
    }
}