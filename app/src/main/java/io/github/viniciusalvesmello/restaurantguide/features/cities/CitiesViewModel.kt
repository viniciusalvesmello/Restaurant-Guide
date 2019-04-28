package io.github.viniciusalvesmello.restaurantguide.features.cities

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import io.github.viniciusalvesmello.restaurantguide.features.cities.model.CityView

class CitiesViewModel : ViewModel() {
    private var _inProcessLoadCities = MutableLiveData<Boolean>()
    val inProcessLoadCities: LiveData<Boolean>
        get() = _inProcessLoadCities
    private val _listCities = MutableLiveData<List<CityView>>()
    private val cities: MutableList<CityView> = mutableListOf()
    val listCities: LiveData<List<CityView>>
        get() = _listCities

    init {
        cities.add(
            CityView(
                id = 67,
                name = "São Paulo",
                imageUrl = "https://cache.marriott.com/marriottassets/destinations/hero/sao-paulo-destination.jpg"
            )
        )
        cities.add(
            CityView(
                id = 66,
                name = "Brasília",
                imageUrl = "https://gideonimaran.files.wordpress.com/2011/07/jkpan1.jpg"
            )
        )
    }

    fun loadCities() {
        _inProcessLoadCities.value = true
        _listCities.value = cities
        _inProcessLoadCities.value = false
    }
}
