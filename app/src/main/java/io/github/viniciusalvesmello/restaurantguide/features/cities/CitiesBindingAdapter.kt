package io.github.viniciusalvesmello.restaurantguide.features.cities

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.github.viniciusalvesmello.restaurantguide.features.cities.model.CityView

object CitiesBindingAdapter {

    @JvmStatic
    @BindingAdapter("android:adapter")
    fun setAdapterCities(recyclerView : RecyclerView, listCityView: List<CityView>){
        recyclerView.adapter = CitiesAdapter(listCityView)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
    }

}