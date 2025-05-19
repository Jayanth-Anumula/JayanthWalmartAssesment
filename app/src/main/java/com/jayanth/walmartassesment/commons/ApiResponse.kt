package com.jayanth.walmartassesment.commons

/**
 * A sealed class representing different states of an API response.
 */
sealed class ApiResponse<out T> {
    /**
     * Represents a successful API response with the resulting data.
     * @param data The data returned from the API.
     */
    data class Success<out T>(val data: T) : ApiResponse<T>()

    /**
     * Represents an error during an API call.
     * @param message The error message returned.
     */
    data class Error(val message: String) : ApiResponse<Nothing>()

    /**
     * Represents a loading state when the API call is in progress.
     */
    data object Loading : ApiResponse<Nothing>()
}
