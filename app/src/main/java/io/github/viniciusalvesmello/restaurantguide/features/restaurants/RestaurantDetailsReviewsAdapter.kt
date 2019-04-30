package io.github.viniciusalvesmello.restaurantguide.features.restaurants

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import io.github.viniciusalvesmello.restaurantguide.R
import io.github.viniciusalvesmello.restaurantguide.databinding.RowRestaurantReviewBinding
import io.github.viniciusalvesmello.restaurantguide.features.restaurants.model.RestaurantReviewView
import kotlinx.android.synthetic.main.row_restaurant_review.view.*

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
            /*if (restaurantReviewView.userProfileImage.isNotEmpty())
                Picasso.get()
                    .load(restaurantReviewView.userProfileImage)
                    .placeholder(R.drawable.no_image)
                    .error(R.drawable.no_image)
                    .resize(100, 100)
                    .into(itemView.circle_image_view_user_profile_image)
            val ratingText = "${restaurantReviewView.rating} - ${restaurantReviewView.ratingDescription}"
            itemView.text_view_review_rating.text = ratingText
            itemView.text_view_date_description.text = restaurantReviewView.dateDescription
            itemView.text_view_review_user_name.text = restaurantReviewView.userName
            itemView.text_view_review_text.text = restaurantReviewView.reviewText*/
        }
    }
}