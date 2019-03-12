package io.github.viniciusalvesmello.remote

import io.github.viniciusalvesmello.remote.features.restaurants.models.CategoriesRestaurantsResponseModel
import io.github.viniciusalvesmello.remote.features.restaurants.models.RestaurantsResponseModel
import io.github.viniciusalvesmello.remote.features.restaurants.models.RestaurantReviewsResponseModel
import io.reactivex.Single
import retrofit2.http.*

internal interface ZomatoApiInterface {
    companion object {
        private const val HEADER_CONTEXT_TYPE = "Content-Type: application/json"
        private const val VERSION_API_ZOMATO = "v2.1"
        private const val PARAM_USER_KEY = "user-key"
        private const val PARAM_ENTITY_ID = "entity_id"
        private const val PARAM_ENTITY_TYPE = "entity_type"
        private const val PARAM_SORT = "sort"
        private const val PARAM_ORDER = "order"
        private const val PARAM_CATEGORY = "category"
        private const val PARAM_COUNT = "count"
        private const val PARAM_START = "start"
        private const val PARAM_RESTAURANT_ID = "res_id"
        private const val URL_GET_SEARCH_RESTAURANTS = "/api/$VERSION_API_ZOMATO/search"
        private const val URL_GET_CATEGORIES_RESTAURANTS = "/api/$VERSION_API_ZOMATO/categories"
        private const val URL_GET_RESTAURANT_REVIEWS = "/api/$VERSION_API_ZOMATO/reviews"
    }

    @Headers(HEADER_CONTEXT_TYPE)
    @GET(URL_GET_CATEGORIES_RESTAURANTS)
    fun categoriesRestaurants(
        @Header(PARAM_USER_KEY) userKey : String
    ) : Single<CategoriesRestaurantsResponseModel>


    @Headers(HEADER_CONTEXT_TYPE)
    @GET(URL_GET_SEARCH_RESTAURANTS)
    fun searchRestaurants(
        @Header(PARAM_USER_KEY) userKey : String,
        @Query(PARAM_ENTITY_ID) entityId : Int,
        @Query(PARAM_ENTITY_TYPE) entityType : String,
        @Query(PARAM_SORT) sort : String,
        @Query(PARAM_ORDER) order : String,
        @Query(PARAM_CATEGORY) category : Int,
        @Query(PARAM_COUNT) count : Int,
        @Query(PARAM_START) start : Int
    ) : Single<RestaurantsResponseModel>

    @Headers(HEADER_CONTEXT_TYPE)
    @GET(URL_GET_RESTAURANT_REVIEWS)
    fun restaurantReviews(
        @Header(PARAM_USER_KEY) userKey : String,
        @Query(PARAM_RESTAURANT_ID) restaurantId : Int,
        @Query(PARAM_COUNT) count : Int,
        @Query(PARAM_START) start : Int
    ) : Single<RestaurantReviewsResponseModel>

}