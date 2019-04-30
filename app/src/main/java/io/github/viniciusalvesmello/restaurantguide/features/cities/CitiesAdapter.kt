package io.github.viniciusalvesmello.restaurantguide.features.cities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.viniciusalvesmello.restaurantguide.features.cities.model.CityView
import io.github.viniciusalvesmello.restaurantguide.R
import io.github.viniciusalvesmello.restaurantguide.databinding.RowCityBinding

class CitiesAdapter(private val listCities: List<CityView>) : RecyclerView.Adapter<CitiesAdapter.CityViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder =
        CityViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_city, parent, false))

    override fun getItemCount(): Int = listCities.count()

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) = holder.bind(listCities[position])

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding : RowCityBinding? = DataBindingUtil.bind(itemView)
        fun bind(cityView: CityView) {
            binding?.city = cityView
            binding?.executePendingBindings()
        }
    }
}