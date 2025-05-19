package com.jayanth.walmartassesment.presentation.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jayanth.walmartassesment.commons.ApiResponse
import com.jayanth.walmartassesment.domain.data.CountryDomain
import com.jayanth.walmartassesment.domain.usecase.CountriesUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.Assert.*

@OptIn(ExperimentalCoroutinesApi::class)
class CountryViewModelTest {

 @get:Rule
 val instantTaskExecutorRule = InstantTaskExecutorRule()

 private val testDispatcher = UnconfinedTestDispatcher()
 private lateinit var countriesUseCase: CountriesUseCase

 private val fakeCountryList = listOf(
  CountryDomain("Tirana", "AL", "Albania", "EU"),
  CountryDomain("The Valley", "AI", "Anguilla", "NA")
 )

 @Before
 fun setUp() {
  Dispatchers.setMain(testDispatcher)
  countriesUseCase = mockk()
 }

 @After
 fun tearDown() {
  Dispatchers.resetMain()
 }

 @Test
 fun `fetchCountries emits Loading then Success`() = runTest {
  val flow = flow {
   emit(ApiResponse.Loading)
   emit(ApiResponse.Success(fakeCountryList))
  }
  coEvery { countriesUseCase() } returns flow

  val viewModel = CountryViewModel(countriesUseCase)

  // Wait for flow to collect
  advanceUntilIdle()

  val state = viewModel.countryState.value
  assertTrue(state is ApiResponse.Success)
  assertEquals(fakeCountryList, (state as ApiResponse.Success).data)
 }

 @Test
 fun `fetchCountries emits Loading then Error`() = runTest {

  val errorMsg = "Something went wrong"
  val flow = flow {
   emit(ApiResponse.Loading)
   emit(ApiResponse.Error(errorMsg))
  }
  coEvery { countriesUseCase() } returns flow

  val viewModel = CountryViewModel(countriesUseCase)

  advanceUntilIdle()

  val state = viewModel.countryState.value
  assertTrue(state is ApiResponse.Error)
  assertEquals(errorMsg, (state as ApiResponse.Error).message)
 }

 @Test
 fun `fetchCountries emits only Loading`() = runTest {

  val flow = flowOf(ApiResponse.Loading)
  coEvery { countriesUseCase() } returns flow


  val viewModel = CountryViewModel(countriesUseCase)

  advanceUntilIdle()

  val state = viewModel.countryState.value
  assertTrue(state is ApiResponse.Loading)
 }
}
