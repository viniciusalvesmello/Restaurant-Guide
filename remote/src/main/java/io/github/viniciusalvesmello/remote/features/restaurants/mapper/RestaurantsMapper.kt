package io.github.viniciusalvesmello.remote.features.restaurants.mapper


import io.github.viniciusalvesmello.data.features.restaurants.model.CategoryRestaurantsEntity
import io.github.viniciusalvesmello.data.features.restaurants.model.RestaurantEntity
import io.github.viniciusalvesmello.data.features.restaurants.model.RestaurantReviewEntity
import io.github.viniciusalvesmello.remote.features.restaurants.models.CategoriesRestaurantsResponseModel
import io.github.viniciusalvesmello.remote.features.restaurants.models.RestaurantsResponseModel
import io.github.viniciusalvesmello.remote.features.restaurants.models.RestaurantReviewsResponseModel


fun CategoriesRestaurantsResponseModel.Companion.Category.toCategoryRestaurantsEntity() = CategoryRestaurantsEntity(
    id = id ?: 0,
    name = name ?: ""
)

fun RestaurantsResponseModel.Companion.RestaurantDetail.toRestaurantEntity() = RestaurantEntity(
    id = id ?: 0,
    name = name ?: "",
    cuisines = cuisines ?: "",
    phoneNumbers = phone_numbers ?: "",
    thumb = thumb ?: "",
    image = featured_image ?: "",
    rating = user_rating?.aggregate_rating ?: "",
    ratingDescription = user_rating?.rating_text ?: "",
    locality = location?.locality_verbose ?: "",
    address = location?.address ?: "",
    latitude = location?.latitude ?: "",
    longitude = location?.longitude ?: ""
)

fun RestaurantReviewsResponseModel.Companion.ReviewDetail.toRestaurantReviewEntity() = RestaurantReviewEntity(
    id = id ?: 0,
    reviewText = review_text ?: "",
    rating = rating ?: 0.0f,
    ratingDescription = rating_text ?: "",
    userName = user?.name ?: "",
    userProfileImage = user?.profile_image ?: "",
    dateDescription = review_time_friendly ?: ""
)