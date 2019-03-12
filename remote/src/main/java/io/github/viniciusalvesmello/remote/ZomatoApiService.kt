package io.github.viniciusalvesmello.remote

import io.github.viniciusalvesmello.remote.features.restaurants.models.CategoriesRestaurantsResponseModel
import io.github.viniciusalvesmello.remote.features.restaurants.models.RestaurantReviewsResponseModel
import io.reactivex.Single
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ZomatoApiService
@Inject constructor(retrofit: Retrofit) : ZomatoApiInterface {

    private val zomatoApi by lazy {
        retrofit.create(ZomatoApiInterface::class.java)
    }

    override fun categoriesRestaurants(userKey: String): Single<CategoriesRestaurantsResponseModel> =
        zomatoApi.categoriesRestaurants(userKey)

    override fun searchRestaurants(
        userKey: String,
        entityId: Int,
        entityType: String,
        sort: String,
        order: String,
        category: Int,
        count: Int,
        start: Int
    ) = zomatoApi.searchRestaurants(
        userKey,
        entityId,
        entityType,
        sort,
        order,
        category,
        count,
        start
    )

    override fun restaurantReviews(
        userKey: String,
        restaurantId: Int,
        count: Int,
        start: Int
    ): Single<RestaurantReviewsResponseModel> = zomatoApi.restaurantReviews(
        userKey,
        restaurantId,
        count,
        start
    )

}