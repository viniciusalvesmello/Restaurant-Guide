package io.github.viniciusalvesmello.restaurantguide.features.restaurants

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.crashlytics.android.Crashlytics
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import io.fabric.sdk.android.Fabric
import io.github.viniciusalvesmello.restaurantguide.R
import io.github.viniciusalvesmello.restaurantguide.features.restaurants.model.RestaurantView
import io.github.viniciusalvesmello.restaurantguide.utils.extension.onBackPressed
import io.github.viniciusalvesmello.restaurantguide.utils.extension.openRestaurantMapsFragment
import io.github.viniciusalvesmello.restaurantguide.utils.extension.toRestaurantView
import kotlinx.android.synthetic.main.restaurant_details_fragment.*

class RestaurantDetailsFragment : Fragment() {

    private val viewModel: RestaurantDetailsViewModel by lazy {
        ViewModelProviders.of(this).get(RestaurantDetailsViewModel::class.java)
    }
    private lateinit var restaurantView: RestaurantView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Fabric.with(context, Crashlytics())
        restaurantView = arguments?.toRestaurantView() ?: RestaurantView.toEmpty()
        return inflater.inflate(R.layout.restaurant_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        constraint_layout_back_pressed.setOnClickListener {
            it.onBackPressed()
        }
        text_view_action_bar_restaurant_name.text = restaurantView.name
        if (restaurantView.image.isNotEmpty())
            Picasso.get()
                .load(restaurantView.image)
                .placeholder(R.drawable.no_image)
                .error(R.drawable.no_image)
                .into(image_view_restaurant)
        val ratingText = "${restaurantView.rating} - ${restaurantView.ratingDescription}"
        text_view_rating_restaurant.text = ratingText
        image_view_googlemaps.visibility =
            if(restaurantView.latitude.isEmpty() || restaurantView.longitude.isEmpty()) View.GONE else View.VISIBLE
        image_view_googlemaps.setOnClickListener {
            it.openRestaurantMapsFragment(restaurantView)
        }
        text_view_cuisines.text = if (restaurantView.cuisines.isNotEmpty()) restaurantView.cuisines
        else getString(R.string.uninformed)
        text_view_phone_numbers.text = if (restaurantView.phoneNumbers.isNotEmpty()) restaurantView.phoneNumbers
        else getString(R.string.uninformed)
        text_view_address.text = if (restaurantView.address.isNotEmpty()) restaurantView.address
        else getString(R.string.uninformed)
        viewModel.inProcessLoadRestaurantReviews.observe(this, Observer { inProcessLoadRestaurantReviews ->
            if (inProcessLoadRestaurantReviews) showProgressBar()
            else hideProgressBar()
        })
        viewModel.listRestaurantReviews.observe(this, Observer { listRestaurantReviews ->
            recycle_view_restaurant_reviews.adapter = RestaurantDetailsReviewsAdapter(listRestaurantReviews)
            recycle_view_restaurant_reviews
                .layoutManager = LinearLayoutManager(context)
            hideProgressBar()
        })
        viewModel.errorRestaurantDetailsViewModel.observe(this, Observer { error ->
            showError(error)
        })
        viewModel.startLoadRestaurantReviews(restaurantView.id)
    }

    private fun showError(error : Throwable) {
        Snackbar.make(
            coordinator_layout_snackbar,
            getString(R.string.we_were_unable_to_process_your_request),
            Snackbar.LENGTH_LONG
        ).show()
        hideProgressBar()
        Crashlytics.logException(error)
    }

    private fun showProgressBar() {
        progress_bar.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        progress_bar.visibility = View.GONE
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.disposable()
    }
}
