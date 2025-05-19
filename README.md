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

---
## Screenshots

| Country List Screen | Video Demo | Without Internet Demo |
|---------------------|---------------------|---------------------|
| <img alt="screenshot"  src="https://github.com/Jayanth-Anumula/JayanthWalmartAssesment/blob/main/output.png" width="250"/> | <video src="https://github.com/user-attachments/assets/18dfaa89-4a51-46c7-b26e-95caf6cb2c3c" width="250" controls/> | <video src="https://github.com/user-attachments/assets/e6969377-88a2-4ab1-9590-09fba6ac1f40" width="250" controls/> |


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

