package io.github.viniciusalvesmello.restaurantguide.features.restaurants

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.github.viniciusalvesmello.restaurantguide.R
import io.github.viniciusalvesmello.restaurantguide.features.restaurants.model.RestaurantView
import io.github.viniciusalvesmello.restaurantguide.utils.extension.isConnected
import io.github.viniciusalvesmello.restaurantguide.utils.extension.openRestaurantDetailsFragment
import kotlinx.android.synthetic.main.row_restaurant.view.*
import kotlinx.android.synthetic.main.row_footer_recycle_view_restaurants.view.*

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
            if (listRestaurants.isEmpty() || viewModel.start == 0)
                holder.itemView.button_last_restaurant.visibility = View.GONE
            else {
                holder.itemView.button_last_restaurant.visibility = View.VISIBLE
                holder.itemView.button_last_restaurant.setOnClickListener {
                    viewModel.updateLastStart()
                    viewModel.startLoadRestaurants()
                }
            }
            if (listRestaurants.isEmpty())
                holder.itemView.button_next_restaurant.visibility = View.GONE
            else {
                holder.itemView.button_next_restaurant.visibility = View.VISIBLE
                holder.itemView.button_next_restaurant.setOnClickListener {
                    viewModel.updateNextStart()
                    viewModel.startLoadRestaurants()
                }
            }
        } else return holder.bind(listRestaurants[position])
    }

    class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(restaurantView: RestaurantView) {
            if (restaurantView.thumb.isNotEmpty())
                Picasso.get()
                    .load(restaurantView.thumb)
                    .placeholder(R.drawable.no_image)
                    .error(R.drawable.no_image)
                    .into(itemView.image_view_restaurant)
            val ratingText = "${restaurantView.rating} - ${restaurantView.ratingDescription}"
            itemView.text_view_rating_restaurant.text = ratingText
            itemView.text_view_name_restaurant.text = restaurantView.name
            itemView.text_view_locality_restaurant.text = restaurantView.locality
            itemView.card_view_restaurant.setOnClickListener {
                it.openRestaurantDetailsFragment(restaurantView)
            }
        }
    }
}