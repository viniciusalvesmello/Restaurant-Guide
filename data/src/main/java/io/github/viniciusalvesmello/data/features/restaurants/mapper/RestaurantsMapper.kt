package io.github.viniciusalvesmello.data.features.restaurants.mapper

import io.github.viniciusalvesmello.data.features.restaurants.model.CategoryRestaurantsEntity
import io.github.viniciusalvesmello.data.features.restaurants.model.RestaurantEntity
import io.github.viniciusalvesmello.data.features.restaurants.model.RestaurantReviewEntity
import io.github.viniciusalvesmello.domain.features.restaurants.models.CategoryRestaurants
import io.github.viniciusalvesmello.domain.features.restaurants.models.Restaurant
import io.github.viniciusalvesmello.domain.features.restaurants.models.RestaurantReview

fun CategoryRestaurantsEntity.toCategoryRestaurants() = CategoryRestaurants(
    id = id,
    name = name
)

fun RestaurantEntity.toRestaurant() = Restaurant(
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

fun RestaurantReviewEntity.toRestaurantReview() = RestaurantReview(
    id = id,
    reviewText = reviewText,
    rating = rating,
    ratingDescription = ratingDescription,
    userName = userName,
    userProfileImage = userProfileImage,
    dateDescription = dateDescription
)