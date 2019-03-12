package io.github.viniciusalvesmello.cache.features.restaurants.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.viniciusalvesmello.cache.features.restaurants.model.CategoryRestaurantsCacheModel
import io.reactivex.Single

@Dao
interface CategoryRestaurantsCacheDao {
    @Query(
        value = "delete " +
                "from category_restaurants " +
                "where (" +
                " (case when ((:categoryRestaurantsDate - categoryRestaurantsDate) >  1) " +
                "   then ((:categoryRestaurantsDate - categoryRestaurantsDate) * 24) " +
                "   else 0 " +
                " end) + " +
                " (case when ((:categoryRestaurantsDate - categoryRestaurantsDate) >= 1) " +
                "   then ((24 - categoryRestaurantsTime) + :categoryRestaurantsTime) " +
                "   else (:categoryRestaurantsTime - categoryRestaurantsTime) " +
                " end)" +
                ") > :expirationTime"
    )
    fun deleteExpiredRestaurantReviewCache(categoryRestaurantsDate: Int, categoryRestaurantsTime: Float, expirationTime: Float)

    @Query(value = "select count(*) from category_restaurants")
    fun countRestaurantReviewCache() : Int

    @Query("select * from category_restaurants")
    fun selectAllRestaurantReviewCache() : Single<List<CategoryRestaurantsCacheModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllRestaurantReviewCache(restaurantReviewsCacheModel: List<CategoryRestaurantsCacheModel>)
}