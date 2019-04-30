package io.github.viniciusalvesmello.restaurantguide.features.restaurants

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
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

}