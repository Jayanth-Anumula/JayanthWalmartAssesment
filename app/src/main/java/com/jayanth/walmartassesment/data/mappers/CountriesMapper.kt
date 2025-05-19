package com.jayanth.walmartassesment.data.mappers

import com.jayanth.walmartassesment.data.entities.Countries
import com.jayanth.walmartassesment.domain.data.CountryDomain

/**
 * Extension function to map a [Countries] entity to [CountryDomain] model used in the domain layer.
 *
 * @return A simplified domain model of the country.
 */
fun Countries.toDomain(): CountryDomain {
    return CountryDomain(
        capital = this.capital,
        code = this.code,
        name = this.name,
        region = this.region
    )
}