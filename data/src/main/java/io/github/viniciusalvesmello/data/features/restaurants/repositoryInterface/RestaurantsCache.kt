package io.github.viniciusalvesmello.data.features.restaurants.repositoryInterface

import io.github.viniciusalvesmello.data.features.restaurants.model.CategoryRestaurantsEntity
import io.github.viniciusalvesmello.data.features.restaurants.model.RestaurantEntity
import io.github.viniciusalvesmello.data.features.restaurants.model.RestaurantReviewEntity
import io.reactivex.Single

interface RestaurantsCache {

    fun deleteExpiredCategoriesRestaurants()

    fun countCategoriesRestaurants() : Int

    fun getCategoriesRestaurants() : Single<List<CategoryRestaurantsEntity>>

    fun storeDataCategoriesRestaurants(listCategoryRestaurantsEntity : List<CategoryRestaurantsEntity>)

    fun deleteExpiredRestaurants()

    fun countRestaurants(
        entityId: Int,
        entityType: String,
        sort: String,
        order: String,
        category: Int,
        count: Int,
        start: Int
    ) : Int

    fun getRestaurants(
        entityId: Int,
        entityType: String,
        sort: String,
        order: String,
        category: Int,
        count: Int,
        start: Int
    ) : Single<List<RestaurantEntity>>

    fun storeDataRestaurants(
        entityId: Int,
        entityType: String,
        sort: String,
        order: String,
        category: Int,
        count: Int,
        start: Int,
        listRestaurantEntity : List<RestaurantEntity>
    )

    fun deleteRestaurantReviews()

    fun countRestaurantReviews(
        restaurantId : Int,
        count: Int,
        start: Int
    ) : Int

    fun getRestaurantReviews(
        restaurantId : Int,
        count: Int,
        start: Int
    ) : Single<List<RestaurantReviewEntity>>

    fun storeDataRestaurantReviews(
        restaurantId : Int,
        count: Int,
        start: Int,
        listRestaurantReviewEntity : List<RestaurantReviewEntity>
    )

}