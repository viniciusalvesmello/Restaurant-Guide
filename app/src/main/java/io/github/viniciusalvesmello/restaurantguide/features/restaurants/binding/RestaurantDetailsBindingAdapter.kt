package io.github.viniciusalvesmello.restaurantguide.features.restaurants.binding

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.viniciusalvesmello.restaurantguide.features.restaurants.RestaurantDetailsReviewsAdapter
import io.github.viniciusalvesmello.restaurantguide.features.restaurants.model.RestaurantReviewView
import io.github.viniciusalvesmello.restaurantguide.features.restaurants.model.RestaurantView
import io.github.viniciusalvesmello.restaurantguide.utils.extension.openRestaurantMapsFragment

object RestaurantDetailsBindingAdapter {

    @JvmStatic
    @BindingAdapter("android:onClick")
    fun onClickImageGoogleMaps(appCompatImageView : AppCompatImageView, restaurantView : RestaurantView) {
        appCompatImageView.setOnClickListener {
            it.openRestaurantMapsFragment(restaurantView)
        }
    }

    @JvmStatic
    @BindingAdapter("android:adapter")
    fun setAdapterRestaurantReviews(recyclerView : RecyclerView, listRestaurantReviews: List<RestaurantReviewView>){
        recyclerView.adapter = RestaurantDetailsReviewsAdapter(listRestaurantReviews)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    }

}