package io.github.viniciusalvesmello.restaurantguide.features.restaurants

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.viniciusalvesmello.restaurantguide.R
import io.github.viniciusalvesmello.restaurantguide.databinding.RowFooterRecycleViewRestaurantsBinding
import io.github.viniciusalvesmello.restaurantguide.databinding.RowRestaurantBinding
import io.github.viniciusalvesmello.restaurantguide.features.restaurants.model.RestaurantView

class RestaurantsAdapter(
    private val listRestaurants: List<RestaurantView>,
    private val viewModel: RestaurantsViewModel
) : RecyclerView.Adapter<RestaurantsAdapter.RestaurantViewHolder>() {

    override fun getItemViewType(position: Int): Int =
        if (position == listRestaurants.size)
            R.layout.row_footer_recycle_view_restaurants
        else R.layout.row_restaurant

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder =
        RestaurantViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))

    override fun getItemCount(): Int = listRestaurants.count() + 1

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        if (position == listRestaurants.size) {
            holder.bindFooter(viewModel)
        } else return holder.bind(listRestaurants[position])
    }

    class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(restaurantView: RestaurantView) {
            val binding : RowRestaurantBinding? = DataBindingUtil.bind(itemView)
            binding?.restaurant = restaurantView
            binding?.executePendingBindings()
        }
        fun bindFooter(viewModel: RestaurantsViewModel) {
            val binding : RowFooterRecycleViewRestaurantsBinding? = DataBindingUtil.bind(itemView)
            binding?.viewmodel = viewModel
            binding?.executePendingBindings()
        }
    }
}