package io.github.viniciusalvesmello.restaurantguide.features.cities

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.crashlytics.android.Crashlytics
import io.github.viniciusalvesmello.restaurantguide.R
import io.fabric.sdk.android.Fabric
import io.github.viniciusalvesmello.restaurantguide.databinding.CitiesFragmentBinding


class CitiesFragment : Fragment() {

    private val viewModel: CitiesViewModel by lazy {
        ViewModelProviders.of(this).get(CitiesViewModel::class.java)
    }
    private lateinit var binding : CitiesFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Fabric.with(context, Crashlytics())
        binding = DataBindingUtil.inflate(inflater, R.layout.cities_fragment, container, false)
        binding.viewmodel = viewModel
        binding.recycleViewCities.layoutManager = LinearLayoutManager(context)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.listCities.observe(this, Observer { listCityView ->
            binding.recycleViewCities.adapter = CitiesAdapter(listCityView)
        })
        viewModel.loadCities()
    }

}
