# Walmart Assessment Country App

This is an Android application built in Kotlin for displaying a list of countries using a endpoint. It follows **Clean Code Architecture**, using **Retrofit**, **ViewModel**, **StateFlow**, and **RecyclerView** to load and display the country list in a user-friendly UI.

---

## Features

- Fetches country data from a remote [API](https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json)
- Displays:
  - Country Name
  - Region
  - Capital
  - ISO Code
- Shows a loading indicator while fetching data
- Displays error messages if API fails or data is invalid
- Displays a Snackbar alert when there's no internet connection

---
## Screenshots

| Country List Screen | Video Demo |
|---------------------|---------------------|
|<img alt="screenshot" height="600" src="https://github.com/Jayanth-Anumula/JayanthWalmartAssesment/blob/main/output.png" width="280"/>|<video src="https://github.com/user-attachments/assets/18dfaa89-4a51-46c7-b26e-95caf6cb2c3c"/>|

---

## Architecture

- **View**: `MainActivity`, `RecyclerView`, `CountryAdapter`, `CountryItemViewHolder`
- **ViewModel**: `CountryViewModel` exposes `StateFlow<ApiResponse<List<CountryDomain>>>`
- **Use Case**: `CountriesUseCase`
- **Repository**: `CountryRepositoryImpl` implements `ICountryRepository`
- **Data Source**: `CountriesApiService` via Retrofit
- **Model Mapping**: `Countries.toDomain()`

---

## Packages

```
com.jayanth.walmartassesment
│
├── commons             # ApiResponse sealed class, Constants
├── data
│   ├── entities        # Countries, Currency, Language (raw model)
│   ├── mappers         # Mapping to domain model
│   ├── remote          # Retrofit client and service
│   └── repositories    # CountryRepositoryImpl
│
├── domain
│   ├── data            # CountryDomain (used by ViewModel/UI)
│   ├── irepository     # ICountryRepository interface
│   └── usecase         # CountriesUseCase
│
├── presentation
│   ├── view            # MainActivity, Adapter, ViewHolder
│   └── viewModel       # CountryViewModel, ViewModelFactory
```

---
## Tech Stack

- **Language**: Kotlin
- **UI**: ViewBinding, RecyclerView
- **Architecture**: MVVM, Clean Code Architecture
- **Networking**: Retrofit + Gson
- **Asynchronous Handling**: Coroutines, ViewModelScope, StateFlow
- **Testing**: MockK

---

## API Used

- **Base URL**: `https://gist.githubusercontent.com/peymano-wmt/`
- **Endpoint**:  
  `32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json`

---

