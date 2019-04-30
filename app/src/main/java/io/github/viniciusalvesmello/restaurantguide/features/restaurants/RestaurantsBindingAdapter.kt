package io.github.viniciusalvesmello.restaurantguide.features.restaurants

import androidx.databinding.BindingAdapter
import com.google.android.material.button.MaterialButton
import com.google.android.material.card.MaterialCardView
import io.github.viniciusalvesmello.restaurantguide.features.restaurants.model.RestaurantView
import io.github.viniciusalvesmello.restaurantguide.utils.extension.openRestaurantDetailsFragment

object RestaurantsBindingAdapter {

    @JvmStatic
    @BindingAdapter("android:onClick")
    fun onClickCardViewRestaurant(materialCardView : MaterialCardView, restaurantView : RestaurantView) {
        materialCardView.setOnClickListener {
            it.openRestaurantDetailsFragment(restaurantView)
        }
    }

    @JvmStatic
    @BindingAdapter("app:onClickButtonLastRestaurant")
    fun onClickButtonLastRestaurant(materialButton : MaterialButton, viewModel : RestaurantsViewModel) {
        materialButton.setOnClickListener {
            viewModel.updateLastStart()
            viewModel.startLoadRestaurants()
        }
    }


    @JvmStatic
    @BindingAdapter("app:onClickButtonNextRestaurant")
    fun onClickButtonNextRestaurant(materialButton : MaterialButton, viewModel : RestaurantsViewModel) {
        materialButton.setOnClickListener {
            viewModel.updateNextStart()
            viewModel.startLoadRestaurants()
        }
    }

}