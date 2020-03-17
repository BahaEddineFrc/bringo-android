package com.bringo.dotit.views


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bringo.dotit.R
import com.bringo.dotit.databinding.TrackingBinding
import com.bringo.dotit.utils.Hell
import com.bringo.dotit.viewmodels.TrackingViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


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
        val rootView: View = binding.root

        initViewModelListeners()

        val mapFragment =
            childFragmentManager.findFragmentById(com.bringo.dotit.R.id.mapi) as SupportMapFragment?

        mapFragment!!.getMapAsync { mMap ->
            mMap.mapType = GoogleMap.MAP_TYPE_NORMAL
            mMap.clear() //clear old markers

            val googlePlex = CameraPosition.builder()
                .target(LatLng(35.8376202,10.6314504))
                .zoom(16f)
                .bearing(0f)
                .tilt(45f)
                .build()

            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 2000, null)

            mMap.addMarker(
                MarkerOptions()
                    .position(LatLng(35.8376202,10.6314504))
                    .title("Iron Man")
                    .snippet("Dish coming!")
            )
        }


        return rootView
    }

    private fun initViewModelListeners() {
        val dishName=arguments!!.getString("dishName","error getting name")
        viewModel.intitializeInfo(dishName)
    }


    override fun onMapReady(googleMap: GoogleMap) {

    }


}
