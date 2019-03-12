package io.github.viniciusalvesmello.restaurantguide.utils.extension

import android.os.Bundle
import io.github.viniciusalvesmello.restaurantguide.features.restaurants.model.RestaurantView

fun RestaurantView.toBundle(): Bundle {
    val argsBundle = Bundle()
    argsBundle.putInt("restaurantId", id)
    argsBundle.putString("restaurantName", this.name)
    argsBundle.putString("restaurantCuisines", this.cuisines)
    argsBundle.putString("phoneNumbers", this.phoneNumbers)
    argsBundle.putString("restaurantThumb", this.thumb)
    argsBundle.putString("restaurantImage", this.image)
    argsBundle.putString("restaurantRating", this.rating)
    argsBundle.putString("restaurantRatingDescription", this.ratingDescription)
    argsBundle.putString("restaurantLocality", this.locality)
    argsBundle.putString("restaurantAddress", this.address)
    argsBundle.putString("restaurantLatitue", this.latitude)
    argsBundle.putString("restaurantLongitude", this.longitude)
    return argsBundle
}