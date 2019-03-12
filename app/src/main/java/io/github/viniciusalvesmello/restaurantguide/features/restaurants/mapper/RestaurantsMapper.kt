package io.github.viniciusalvesmello.restaurantguide.features.restaurants.mapper

import io.github.viniciusalvesmello.domain.features.restaurants.models.CategoryRestaurants
import io.github.viniciusalvesmello.domain.features.restaurants.models.Restaurant
import io.github.viniciusalvesmello.domain.features.restaurants.models.RestaurantReview
import io.github.viniciusalvesmello.restaurantguide.features.restaurants.model.CategoryRestaurantsView
import io.github.viniciusalvesmello.restaurantguide.features.restaurants.model.RestaurantView
import io.github.viniciusalvesmello.restaurantguide.features.restaurants.model.RestaurantReviewView

fun CategoryRestaurants.toCategoryRestaurantsView() = CategoryRestaurantsView(
    id = id,
    name = name
)

fun Restaurant.toRestaurantView() = RestaurantView(
    id = id,
    name = name,
    cuisines = cuisines,
    phoneNumbers = phoneNumbers,
    thumb = thumb,
    image = image,
    rating = rating,
    ratingDescription = ratingDescription,
    locality = locality,
    address = address,
    latitude = latitude,
    longitude = longitude
)

fun RestaurantReview.toRestaurantReviewView() = RestaurantReviewView(
    id = id,
    reviewText = reviewText,
    rating = rating,
    ratingDescription = ratingDescription,
    userName = userName,
    userProfileImage = userProfileImage,
    dateDescription = dateDescription
)