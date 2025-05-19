package com.jayanth.walmartassesment.data.repositories

import com.jayanth.walmartassesment.commons.ApiResponse
import com.jayanth.walmartassesment.data.entities.Countries
import com.jayanth.walmartassesment.data.entities.Currency
import com.jayanth.walmartassesment.data.entities.Language

import com.jayanth.walmartassesment.data.mappers.toDomain
import com.jayanth.walmartassesment.data.remote.CountriesApiService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response


class CountryRepositoryImplTest {

 private lateinit var apiService: CountriesApiService
 private lateinit var repository: CountryRepositoryImpl

 private val fakeDtoList = listOf(
  Countries(
   capital = "Tirana",
   code = "AL",
   currency = Currency(
    code = "ALL",
    name = "Albanian lek",
    symbol = "L"
   ),
   flag = "https://restcountries.eu/data/alb.svg",
   language = Language(
    code = "sq",
    name = "Albanian"
   ),
   name = "Albania",
   region = "EU"
  ),
  Countries(
   capital = "The Valley",
   code = "AI",
   currency = Currency(
    code = "XCD",
    name = "East Caribbean dollar",
    symbol = "$"
   ),
   flag = "https://restcountries.eu/data/aia.svg",
   language = Language(
    code = "en",
    name = "English"
   ),
   name = "Anguilla",
   region = "NA"
  )
 )


 private val fakeDomainList = fakeDtoList.map { it.toDomain() }

 @Before
 fun setUp() {
  apiService = mockk()
  repository = CountryRepositoryImpl(apiService)
 }

 @Test
 fun `getCountries emits Loading then Success on successful response`() = runTest {

  coEvery { apiService.getCountries() } returns Response.success(fakeDtoList)

  val emissions = repository.getCountries().toList()
  assertEquals(ApiResponse.Loading, emissions[0])
  assert(emissions[1] is ApiResponse.Success)
  assertEquals(fakeDomainList, (emissions[1] as ApiResponse.Success).data)
 }

 @Test
 fun `getCountries emits Loading then Error on API failure response`() = runTest {
  val errorBody = ResponseBody.create("application/json".toMediaType(), "{\"message\":\"Not Found\"}")
  coEvery { apiService.getCountries() } returns Response.error(404, errorBody)

  val emissions = repository.getCountries().toList()

  assertEquals(ApiResponse.Loading, emissions[0])
  assert(emissions[1] is ApiResponse.Error)
  assert((emissions[1] as ApiResponse.Error).message.contains("404"))
 }

 @Test
 fun `getCountries emits Loading then Error on exception thrown`() = runTest {

  coEvery { apiService.getCountries() } throws RuntimeException("Timeout")


  val emissions = repository.getCountries().toList()


  assertEquals(ApiResponse.Loading, emissions[0])
  assert(emissions[1] is ApiResponse.Error)
  assertEquals("Exception: Timeout", (emissions[1] as ApiResponse.Error).message)
 }
}
