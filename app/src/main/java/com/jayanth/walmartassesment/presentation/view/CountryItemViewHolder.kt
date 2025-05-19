package com.jayanth.walmartassesment.presentation.view

import androidx.recyclerview.widget.RecyclerView
import com.example.jayanthwalmartassesment.R
import com.example.jayanthwalmartassesment.databinding.CountryItemBinding

import com.jayanth.walmartassesment.domain.data.CountryDomain

/**
 * ViewHolder class for displaying a single country's details in the RecyclerView.
 *
 * @property binding The view binding object for the country item layout.
 */
class CountryItemViewHolder(val binding: CountryItemBinding):RecyclerView.ViewHolder(binding.root) {

    /**
     * Binds the given [CountryDomain] data to the item UI elements.
     *
     * @param country The [CountryDomain] instance representing the country's details.
     */

    fun bind(country: CountryDomain) {
        with(binding) {
            nameRegionTv.text = itemView.context.getString(
                R.string.name_region_format,
                country.name,
                country.region
            )
            codeTv.text = country.code
            capitalTv.text = country.capital
        }
    }

}