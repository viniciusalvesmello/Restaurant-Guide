package io.github.viniciusalvesmello.restaurantguide.features.cities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.github.viniciusalvesmello.restaurantguide.features.cities.model.CityView
import kotlinx.android.synthetic.main.row_city.view.*
import io.github.viniciusalvesmello.restaurantguide.R
import io.github.viniciusalvesmello.restaurantguide.utils.extension.openRestaurantsFragment


class CitiesAdapter(private val listCities: List<CityView>) : RecyclerView.Adapter<CitiesAdapter.CityViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder =
        CityViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.row_city, parent, false))

    override fun getItemCount(): Int = listCities.count()

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) = holder.bind(listCities[position])

    class CityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(cityView: CityView) {
            Picasso.get()
                .load(cityView.imageUrl)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(itemView.image_view_city)
            itemView.text_view_city_name.text = cityView.name
            itemView.card_view_city.setOnClickListener {
                it.openRestaurantsFragment(cityView)
            }
        }
    }
}