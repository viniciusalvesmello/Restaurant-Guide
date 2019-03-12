package io.github.viniciusalvesmello.cache.features.restaurants.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.viniciusalvesmello.cache.features.restaurants.model.RestaurantReviewCacheModel
import io.reactivex.Single


@Dao
interface RestaurantReviewCacheDao {
    @Query(
        value = "delete " +
                "from restaurant_review " +
                "where (" +
                " (case when ((:restaurantReviewDate - restaurantReviewDate) >  1) " +
                "   then ((:restaurantReviewDate - restaurantReviewDate) * 24) " +
                "   else 0 " +
                " end) + " +
                " (case when ((:restaurantReviewDate - restaurantReviewDate) >= 1) " +
                "   then ((24 - restaurantReviewTime) + :restaurantReviewTime) " +
                "   else (:restaurantReviewTime - restaurantReviewTime) " +
                " end)" +
                ") > :expirationTime"
    )
    fun deleteExpiredRestaurantReviewCache(
        restaurantReviewDate: Int,
        restaurantReviewTime: Float,
        expirationTime: Float
    )

    @Query(
        value = "select count(*) " +
                "from restaurant_review " +
                "where restaurantReviewRestaurantId = :restaurantReviewRestaurantId " +
                "and   restaurantReviewCount        = :restaurantReviewCount " +
                "and   restaurantReviewStart        = :restaurantReviewStart"
    )
    fun countRestaurantReviewCache(
        restaurantReviewRestaurantId: Int,
        restaurantReviewCount: Int,
        restaurantReviewStart: Int
    ): Int

    @Query(
        value = "select * " +
                "from restaurant_review " +
                "where restaurantReviewRestaurantId = :restaurantReviewRestaurantId " +
                "and   restaurantReviewCount        = :restaurantReviewCount " +
                "and   restaurantReviewStart        = :restaurantReviewStart"
    )
    fun selectAllRestaurantReviewCache(
        restaurantReviewRestaurantId: Int,
        restaurantReviewCount: Int,
        restaurantReviewStart: Int
    ): Single<List<RestaurantReviewCacheModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRestaurantReviewCache(restaurantReviewsCacheModel: List<RestaurantReviewCacheModel>)
}