# 🌍 Walmart Assessment Country App

This is an Android application built in Kotlin for displaying a list of countries using a public JSON endpoint. It follows **Clean Code Architecture**, using **Retrofit**, **ViewModel**, **StateFlow**, and **RecyclerView** to load and display the country list in a user-friendly UI.

---

## 📱 Features

- Fetches country data from a remote API
- Displays:
  - Country Name
  - Region
  - Capital
  - ISO Code
- Uses sealed `ApiResponse` to handle loading, success, and error states
- View updates based on reactive `StateFlow` from the ViewModel
- Error handling and empty state support
- Clean UI using Material Design

---
## Screenshots

| Country List Screen |
|---------------------|
| ![screenshot](https://github.com/Jayanth-Anumula/JayanthWalmartAssesment/blob/main/output.png) |


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
- **UI**: ViewBinding, RecyclerView, Material Design
- **Architecture**: MVVM, Clean Architecture
- **Networking**: Retrofit + Gson
- **Asynchronous Handling**: Coroutines, ViewModelScope, StateFlow
- **Testing**: MockK

---

## API Used

- **Base URL**: `https://gist.githubusercontent.com/peymano-wmt/`
- **Endpoint**:  
  `32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json`

---

## Getting Started

1. Clone the project
2. Open in Android Studio
3. Run the app on a real or virtual device with internet access

---

## Future Improvements

- Add search functionality
- Add country details screen
- Use Paging3 for large datasets

---

## Author

**Jayanth Anumula**
This project is part of a Walmart Android Developer Assessment.
---