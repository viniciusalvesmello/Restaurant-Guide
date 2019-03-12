package io.github.viniciusalvesmello.restaurantguide.utils.extension

import android.os.Bundle
import io.github.viniciusalvesmello.restaurantguide.features.cities.model.CityView

fun CityView.toBundle(): Bundle {
    val argsBundle = Bundle()
    argsBundle.putInt("cityId", this.id)
    argsBundle.putString("cityName", this.name)
    argsBundle.putString("cityImageUrl", this.imageUrl)
    return argsBundle
}