package io.github.viniciusalvesmello.restaurantguide.features.restaurants

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.viniciusalvesmello.restaurantguide.R
import io.github.viniciusalvesmello.restaurantguide.databinding.RowRestaurantReviewBinding
import io.github.viniciusalvesmello.restaurantguide.features.restaurants.model.RestaurantReviewView

class RestaurantDetailsReviewsAdapter(
    private val listRestaurantReviews: List<RestaurantReviewView>
) : RecyclerView.Adapter<RestaurantDetailsReviewsAdapter.RestaurantReviewViewHolder>() {

    override fun getItemViewType(position: Int): Int = R.layout.row_restaurant_review

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantReviewViewHolder =
        RestaurantReviewViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))

    override fun getItemCount(): Int = listRestaurantReviews.size

    override fun onBindViewHolder(holder: RestaurantReviewViewHolder, position: Int) =
        holder.bind(listRestaurantReviews[position])

    class RestaurantReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding : RowRestaurantReviewBinding? =  DataBindingUtil.bind(itemView)
        fun bind(restaurantReviewView: RestaurantReviewView) {
            binding?.restaurantReview = restaurantReviewView
            binding?.executePendingBindings()
        }
    }
}