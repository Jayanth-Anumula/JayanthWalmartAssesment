package com.jayanth.walmartassesment.data.entities

/**
 * Data class representing a country.
 */
data class Countries(
    val capital: String,
    val code: String,
    val currency: Currency,
    val flag: String,
    val language: Language,
    val name: String,
    val region: String
)