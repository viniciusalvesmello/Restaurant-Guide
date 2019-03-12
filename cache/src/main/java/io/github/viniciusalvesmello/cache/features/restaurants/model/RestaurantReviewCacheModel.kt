package io.github.viniciusalvesmello.cache.features.restaurants.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "restaurant_review")
data class RestaurantReviewCacheModel(
    @PrimaryKey(autoGenerate = false)
    var restaurantReviewRestaurantId: Int,
    var restaurantReviewCount: Int,
    var restaurantReviewStart: Int,
    var restaurantReviewId: Int,
    var restaurantReviewText: String,
    var restaurantReviewRating: Float,
    var restaurantReviewRatingDescription: String,
    var restaurantReviewUserName: String,
    var restaurantReviewUserProfileImage: String,
    var restaurantReviewDateDescription: String,
    var restaurantReviewDate: Int,
    var restaurantReviewTime: Float
)