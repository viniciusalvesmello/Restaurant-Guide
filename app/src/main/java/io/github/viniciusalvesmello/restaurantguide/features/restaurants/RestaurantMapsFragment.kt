package io.github.viniciusalvesmello.restaurantguide.features.restaurants

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.crashlytics.android.Crashlytics
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import io.fabric.sdk.android.Fabric
import io.github.viniciusalvesmello.restaurantguide.R
import io.github.viniciusalvesmello.restaurantguide.features.restaurants.model.RestaurantView
import io.github.viniciusalvesmello.restaurantguide.utils.extension.onBackPressed
import io.github.viniciusalvesmello.restaurantguide.utils.extension.toRestaurantView
import kotlinx.android.synthetic.main.fragment_restaurant_maps.*

class RestaurantMapsFragment : Fragment() {

    companion object {
        private const val ZOOM_GOOGLE_MAPS = 15.0f
    }

    private lateinit var restaurantView: RestaurantView
    private lateinit var latLngRestaurant: LatLng
    private val mapFragment: SupportMapFragment by lazy {
        SupportMapFragment.newInstance()
    }

    init {
        mapFragment.getMapAsync { googleMap ->
            if (googleMap != null) {
                latLngRestaurant = LatLng(restaurantView.latitude.toDouble(), restaurantView.longitude.toDouble())
                googleMap.addMarker(MarkerOptions().position(latLngRestaurant).title(restaurantView.name))
                googleMap.moveCamera(
                    CameraUpdateFactory.newLatLngZoom(
                        latLngRestaurant,
                        ZOOM_GOOGLE_MAPS
                    )
                )
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Fabric.with(context, Crashlytics())
        restaurantView = arguments?.toRestaurantView() ?: RestaurantView.toEmpty()
        return inflater.inflate(R.layout.fragment_restaurant_maps, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        constraint_layout_back_pressed.setOnClickListener {
            it.onBackPressed()
        }
        text_view_action_bar_restaurant_name.text = restaurantView.name
        childFragmentManager.beginTransaction().replace(R.id.frame_layout_google_maps, mapFragment).commit()
    }

}
