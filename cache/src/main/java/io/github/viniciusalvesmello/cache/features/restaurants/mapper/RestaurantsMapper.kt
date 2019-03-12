package io.github.viniciusalvesmello.cache.features.restaurants.mapper

import io.github.viniciusalvesmello.cache.features.restaurants.model.CategoryRestaurantsCacheModel
import io.github.viniciusalvesmello.cache.features.restaurants.model.RestaurantCacheModel
import io.github.viniciusalvesmello.cache.features.restaurants.model.RestaurantReviewCacheModel
import io.github.viniciusalvesmello.data.features.restaurants.model.CategoryRestaurantsEntity
import io.github.viniciusalvesmello.data.features.restaurants.model.RestaurantEntity
import io.github.viniciusalvesmello.data.features.restaurants.model.RestaurantReviewEntity

fun CategoryRestaurantsCacheModel.toCategoryRestaurantsEntity() = CategoryRestaurantsEntity(
    id = categoryRestaurantsId,
    name = categoryRestaurantsName
)

fun CategoryRestaurantsEntity.toCategoryRestaurantsCacheModel(
    categoryRestaurantsDate: Int,
    categoryRestaurantsTime: Float
) =
    CategoryRestaurantsCacheModel(
        categoryRestaurantsId = id,
        categoryRestaurantsName = name,
        categoryRestaurantsDate = categoryRestaurantsDate,
        categoryRestaurantsTime = categoryRestaurantsTime
    )

fun RestaurantCacheModel.toRestaurantEntity() = RestaurantEntity(
    id = restaurantEntityId,
    name = restaurantName,
    cuisines = restaurantCuisines,
    phoneNumbers = restaurantPhoneNumbers,
    thumb = restaurantThumb,
    image = restaurantImage,
    rating = restaurantRating,
    ratingDescription = restaurantRatingDescription,
    locality = restaurantLocality,
    address = restaurantAddress,
    latitude = restaurantLatitude,
    longitude = restaurantLongitude
)

fun RestaurantEntity.toRestaurantCacheModel(
    restaurantEntityId: Int,
    restaurantEntityType: String,
    restaurantSort: String,
    restaurantOrder: String,
    restaurantCategory: Int,
    restaurantCount: Int,
    restaurantStart: Int,
    restaurantDate: Int,
    restaurantTime: Float
) = RestaurantCacheModel(
    restaurantEntityId = restaurantEntityId,
    restaurantEntityType = restaurantEntityType,
    restaurantSort = restaurantSort,
    restaurantOrder = restaurantOrder,
    restaurantCategory = restaurantCategory,
    restaurantCount = restaurantCount,
    restaurantStart = restaurantStart,
    restaurantId = id,
    restaurantName = name,
    restaurantCuisines = cuisines,
    restaurantPhoneNumbers = phoneNumbers,
    restaurantThumb = thumb,
    restaurantImage = image,
    restaurantRating = rating,
    restaurantRatingDescription = ratingDescription,
    restaurantLocality = locality,
    restaurantAddress = address,
    restaurantLatitude = latitude,
    restaurantLongitude = longitude,
    restaurantDate = restaurantDate,
    restaurantTime = restaurantTime
)

fun RestaurantReviewCacheModel.toRestaurantReviewEntity() = RestaurantReviewEntity(
    id = restaurantReviewId,
    reviewText = restaurantReviewText,
    rating = restaurantReviewRating,
    ratingDescription = restaurantReviewRatingDescription,
    userName = restaurantReviewUserName,
    userProfileImage = restaurantReviewUserProfileImage,
    dateDescription = restaurantReviewDateDescription
)

fun RestaurantReviewEntity.toRestaurantReviewCacheModel(
    restaurantReviewRestaurantId: Int,
    restaurantReviewCount: Int,
    restaurantReviewStart: Int,
    restaurantReviewDate: Int,
    restaurantReviewTime: Float
) = RestaurantReviewCacheModel(
    restaurantReviewRestaurantId = restaurantReviewRestaurantId,
    restaurantReviewCount = restaurantReviewCount,
    restaurantReviewStart = restaurantReviewStart,
    restaurantReviewId = id,
    restaurantReviewText = reviewText,
    restaurantReviewRating = rating,
    restaurantReviewRatingDescription = ratingDescription,
    restaurantReviewUserName = userName,
    restaurantReviewUserProfileImage = userProfileImage,
    restaurantReviewDateDescription = dateDescription,
    restaurantReviewDate = restaurantReviewDate,
    restaurantReviewTime = restaurantReviewTime
)