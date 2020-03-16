package com.bringo.dotit.views


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.bringo.dotit.R
import com.bringo.dotit.databinding.TrackingBinding
import com.bringo.dotit.utils.Hell
import com.bringo.dotit.viewmodels.TrackingViewModel
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.lang.Exception

class DeliveryTracking : Fragment(), OnMapReadyCallback {

    lateinit var binding : TrackingBinding
    lateinit var viewModel: TrackingViewModel
    private lateinit var mMap: GoogleMap
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_delivery_tracking, container, false)
        viewModel = ViewModelProviders.of(this).get(TrackingViewModel::class.java)
        binding.categoryviewmodel=viewModel

        initViewModelListeners()

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {

        }catch (e:Exception){
            Hell("causeee $e // ${childFragmentManager}" +
                    "// ${childFragmentManager
                        .findFragmentById(R.id.mapi)==null}")
        }
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.mapi) as MapFragment
        mapFragment.getMapAsync(this)
    }

    private fun initViewModelListeners() {
        val dishName=arguments!!.getString("dishName","error getting name")
        viewModel.intitializeInfo(dishName)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        Hell("map is readyyyyyy")
        val restau = LatLng(35.8376202,10.6314504)
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.addMarker(MarkerOptions().position(restau).title("Planet food"))
        mMap.animateCamera(CameraUpdateFactory.newLatLng(restau))

    }


}
