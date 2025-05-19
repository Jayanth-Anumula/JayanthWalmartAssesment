package com.jayanth.walmartassesment.domain.usecase

import com.jayanth.walmartassesment.commons.ApiResponse
import com.jayanth.walmartassesment.domain.irepository.ICountryRepository
import com.jayanth.walmartassesment.domain.data.CountryDomain
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class CountriesUseCaseTest {

 private lateinit var repository: ICountryRepository
 private lateinit var useCase: CountriesUseCase

 private val fakeCountryList = listOf(
  CountryDomain("Tirana", "AL", "Albania", "EU"),
  CountryDomain("The Valley", "AI", "Anguilla", "NA")
 )

 @Before
 fun setup() {
  repository = mockk()
  useCase = CountriesUseCase(repository)
 }

 @Test
 fun `invoke returns Loading then Success`() = runTest {

  coEvery { repository.getCountries() } returns flow {
   emit(ApiResponse.Loading)
   emit(ApiResponse.Success(fakeCountryList))
  }

  val resultFlow = useCase.invoke()
  val first = resultFlow.first()

  assertEquals(ApiResponse.Loading, first)
 }

 @Test
 fun `invoke returns Error response`() = runTest {
  val errorMessage = "Network failure"
  coEvery { repository.getCountries() } returns flow {
   emit(ApiResponse.Error(errorMessage))
  }

  val result = useCase.invoke().first()

  assert(result is ApiResponse.Error)
  assertEquals(errorMessage, (result as ApiResponse.Error).message)
 }

 @Test
 fun `invoke returns Success with countries`() = runTest {
  coEvery { repository.getCountries() } returns flow {
   emit(ApiResponse.Success(fakeCountryList))
  }

  val result = useCase.invoke().first()

  assert(result is ApiResponse.Success)
  assertEquals(fakeCountryList, (result as ApiResponse.Success).data)
 }
}
