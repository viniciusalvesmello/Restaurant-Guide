package io.github.viniciusalvesmello.restaurantguide.utils.extension

import android.view.View
import androidx.navigation.findNavController
import io.github.viniciusalvesmello.restaurantguide.R
import io.github.viniciusalvesmello.restaurantguide.features.cities.model.CityView
import io.github.viniciusalvesmello.restaurantguide.features.restaurants.model.RestaurantView
import io.github.viniciusalvesmello.restaurantguide.utils.Animation

fun View.openRestaurantsFragment(cityView: CityView) {
    this.findNavController().navigate(
        R.id.restaurantsFragment,
        cityView.toBundle(),
        Animation.transitionFragment(),
        null
    )
}

fun View.openRestaurantDetailsFragment(restaurantView: RestaurantView) {
    this.findNavController().navigate(
        R.id.restaurantDetailsFragment,
        restaurantView.toBundle(),
        Animation.transitionFragment(),
        null
    )
}

fun View.openRestaurantMapsFragment(restaurantView: RestaurantView) {
    this.findNavController().navigate(
        R.id.restaurantMapsFragment,
        restaurantView.toBundle(),
        Animation.transitionFragment(),
        null
    )
}

fun View.onBackPressed() {
    this.findNavController().popBackStack()
}