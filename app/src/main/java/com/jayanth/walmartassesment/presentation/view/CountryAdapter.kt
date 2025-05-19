package com.jayanth.walmartassesment.presentation.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jayanthwalmartassesment.databinding.CountryItemBinding

import com.jayanth.walmartassesment.domain.data.CountryDomain

/**
 * Adapter class for displaying a list of countries in a [RecyclerView].
 *
 * @property countries The list of [CountryDomain] items to be displayed.
 */
class CountryAdapter(private var countries: List<CountryDomain>):RecyclerView.Adapter<CountryItemViewHolder>() {
    private lateinit var binding: CountryItemBinding

    /**
     * Creates a new [CountryItemViewHolder] by inflating the item layout.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = CountryItemBinding.inflate(inflater,parent, false)
        return CountryItemViewHolder(binding)
    }

    /**
     * Returns the total number of items in the dataset.
     */
    override fun getItemCount(): Int {
        return countries.size
    }

    /**
     * Binds the data at the given [position] to the [CountryItemViewHolder].
     */
    override fun onBindViewHolder(holder: CountryItemViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    /**
     * Updates the dataset with a new list of countries and refreshes the UI.
     *
     * @param newList The new list of [CountryDomain] to display.
     */
    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<CountryDomain>) {
        countries = newList
        notifyDataSetChanged()
    }
}