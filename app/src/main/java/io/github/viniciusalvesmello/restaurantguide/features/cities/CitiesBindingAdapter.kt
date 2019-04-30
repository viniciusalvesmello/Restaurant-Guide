package io.github.viniciusalvesmello.restaurantguide.features.cities

import androidx.databinding.BindingAdapter
import com.google.android.material.card.MaterialCardView
import io.github.viniciusalvesmello.restaurantguide.features.cities.model.CityView
import io.github.viniciusalvesmello.restaurantguide.utils.extension.openRestaurantsFragment

object CitiesBindingAdapter {

    @JvmStatic
    @BindingAdapter("android:onClick")
    fun onClickImageGoogleMaps(materialCardView : MaterialCardView, cityView : CityView) {
        materialCardView.setOnClickListener {
            it.openRestaurantsFragment(cityView)
        }
    }

}