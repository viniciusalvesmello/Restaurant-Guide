package io.github.viniciusalvesmello.restaurantguide.utils.extension

import android.os.Bundle
import io.github.viniciusalvesmello.restaurantguide.features.cities.model.CityView
import io.github.viniciusalvesmello.restaurantguide.features.restaurants.model.RestaurantView

fun Bundle.toCityView(): CityView = CityView(
    this.getInt("cityId", 0),
    this.getString("cityName", ""),
    this.getString("cityImageUrl", "")
)

fun Bundle.toRestaurantView(): RestaurantView = RestaurantView(
    this.getInt("restaurantId", 0),
    this.getString("restaurantName", ""),
    this.getString("restaurantCuisines", ""),
    this.getString("phoneNumbers", ""),
    this.getString("restaurantThumb", ""),
    this.getString("restaurantImage", ""),
    this.getString("restaurantRating", ""),
    this.getString("restaurantRatingDescription", ""),
    this.getString("restaurantLocality", ""),
    this.getString("restaurantAddress", ""),
    this.getString("restaurantLatitue", ""),
    this.getString("restaurantLongitude", "")
)