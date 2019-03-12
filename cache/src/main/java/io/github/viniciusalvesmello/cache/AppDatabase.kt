package io.github.viniciusalvesmello.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.viniciusalvesmello.cache.features.restaurants.dao.CategoryRestaurantsCacheDao
import io.github.viniciusalvesmello.cache.features.restaurants.dao.RestaurantCacheDao
import io.github.viniciusalvesmello.cache.features.restaurants.dao.RestaurantReviewCacheDao
import io.github.viniciusalvesmello.cache.features.restaurants.model.CategoryRestaurantsCacheModel
import io.github.viniciusalvesmello.cache.features.restaurants.model.RestaurantCacheModel
import io.github.viniciusalvesmello.cache.features.restaurants.model.RestaurantReviewCacheModel

@Database(
    entities = [
        (CategoryRestaurantsCacheModel::class),
        (RestaurantCacheModel::class),
        (RestaurantReviewCacheModel::class)
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun categoryRestaurantsCacheDao() : CategoryRestaurantsCacheDao

    abstract fun restaurantCacheDao() : RestaurantCacheDao

    abstract fun restaurantReviewCacheDao() : RestaurantReviewCacheDao

}