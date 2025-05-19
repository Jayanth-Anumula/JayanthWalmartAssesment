package com.jayanth.walmartassesment.domain.data

/**
 * Domain model representing a simplified view of a country used across the app’s business logic layer.
 */
data class CountryDomain(
    val capital: String,
    val code: String,
    val name: String,
    val region: String
) {
}