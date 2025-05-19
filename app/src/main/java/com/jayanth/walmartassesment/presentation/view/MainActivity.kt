package com.jayanth.walmartassesment.presentation.view

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jayanthwalmartassesment.databinding.ActivityMainBinding
import com.jayanth.walmartassesment.commons.ApiResponse
import com.jayanth.walmartassesment.data.remote.CountriesApiClient
import com.jayanth.walmartassesment.data.remote.CountriesApiService
import com.jayanth.walmartassesment.data.repositories.CountryRepositoryImpl

import com.jayanth.walmartassesment.domain.usecase.CountriesUseCase
import com.jayanth.walmartassesment.presentation.viewModel.CountryViewModel
import com.jayanth.walmartassesment.presentation.viewModel.CountryViewModelFactory

/**
 * [MainActivity] displays a list of countries using a RecyclerView.
 *
 * Responsibilities:
 * - Sets up the UI using ViewBinding
 * - Observes state from [CountryViewModel]
 * - Handles loading, success, and error UI states
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: CountryViewModel
    private lateinit var adapter: CountryAdapter

    /**
     * Initializes the activity, sets up UI components, ViewModel, and observers.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupViewModel()
        setUpObservers()
    }

    /**
     * Observes the [countryState] from the ViewModel and updates the UI accordingly.
     */
    private fun setUpObservers() {
        lifecycleScope.launchWhenStarted {
            viewModel.countryState.collect { response ->
                when (response) {
                    is ApiResponse.Loading -> showLoading()
                    is ApiResponse.Success -> {
                        showContent()
                        adapter.updateData(response.data)
                    }
                    is ApiResponse.Error -> showError(response.message)
                }
            }
        }
    }

    /**
     * Creates the ViewModel and injects dependencies.
     */
    private fun setupViewModel() {
        val apiService = CountriesApiClient.retrofit.create(CountriesApiService::class.java)
        val repository = CountryRepositoryImpl(apiService)
        val useCase = CountriesUseCase(repository)
        val factory = CountryViewModelFactory(useCase)
        viewModel = ViewModelProvider(this, factory)[CountryViewModel::class.java]
    }

    /**
     * Configures the RecyclerView and sets the adapter.
     */
    private fun setupRecyclerView() {
        adapter = CountryAdapter(emptyList())
        binding.countriesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.countriesRecyclerView.adapter = adapter
    }

    /**
     * Shows the loading state UI.
     */
    private fun showLoading() {
        with(binding) {
            progressBar.visibility = View.VISIBLE
            countriesRecyclerView.visibility = View.GONE
            errorTV.visibility = View.GONE
        }
    }

    /**
     * Displays the list of countries and hides other states.
     */
    private fun showContent() {
        with(binding) {
            progressBar.visibility = View.GONE
            countriesRecyclerView.visibility = View.VISIBLE
            errorTV.visibility = View.GONE
        }
    }

    /**
     * Displays an error message if the data load fails.
     *
     * @param message The error message to display.
     */
    private fun showError(message: String) {
        with(binding) {
            progressBar.visibility = View.GONE
            countriesRecyclerView.visibility = View.GONE
            errorTV.visibility = View.VISIBLE
            errorTV.text = message
        }
    }
}
