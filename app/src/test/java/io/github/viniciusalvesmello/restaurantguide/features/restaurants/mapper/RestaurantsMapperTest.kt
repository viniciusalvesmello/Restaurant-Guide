package io.github.viniciusalvesmello.restaurantguide.features.restaurants.mapper

import io.github.viniciusalvesmello.domain.features.restaurants.models.CategoryRestaurants
import io.github.viniciusalvesmello.restaurantguide.features.restaurants.model.CategoryRestaurantsView
import io.github.viniciusalvesmello.domain.features.restaurants.models.Restaurant
import io.github.viniciusalvesmello.domain.features.restaurants.models.RestaurantReview
import io.github.viniciusalvesmello.restaurantguide.features.restaurants.model.RestaurantView
import io.github.viniciusalvesmello.restaurantguide.features.restaurants.model.RestaurantReviewView
import org.junit.Assert.assertTrue
import org.junit.Test


class RestaurantsMapperTest {

    @Test fun extensionFunction_CategoryRestaurantsToCategoryRestaurantsView_CategoryRestaurantsView() {
        assertTrue(CategoryRestaurants(
            id = 0,
            name = ""
        ).toCategoryRestaurantsView() == CategoryRestaurantsView.toEmpty())
    }

    @Test fun extensionFunction_RestaurantToRestaurantView_EqualsRestaurantView() {
        assertTrue(Restaurant(
            id = 0,
            name = "",
            cuisines = "",
            phoneNumbers = "",
            thumb = "",
            image = "",
            rating = "",
            ratingDescription = "",
            locality = "",
            address = "",
            latitude = "",
            longitude = ""
        ).toRestaurantView() == RestaurantView.toEmpty())
    }

    @Test fun extensionFunction_RestaurantReviewToRestaurantReviewView_CategoryRestaurantsView() {
        assertTrue(RestaurantReview(
                id = 0,
                reviewText = "",
                rating = 0.0f,
                ratingDescription = "",
                userName = "",
                userProfileImage = "",
                dateDescription = ""
            ).toRestaurantReviewView() == RestaurantReviewView.toEmpty())
    }

}