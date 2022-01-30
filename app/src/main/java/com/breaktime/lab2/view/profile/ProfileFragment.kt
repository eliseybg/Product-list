package com.breaktime.lab2.view.profile

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.breaktime.lab2.R
import com.breaktime.lab2.databinding.FragmentProfileBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class ProfileFragment : Fragment(), OnMapReadyCallback {
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_profile, container, false
        )
        binding.info.setOnClickListener {
            findNavController().navigate(R.id.aboutFragment)
        }
        val map = childFragmentManager.findFragmentById(binding.map.id) as SupportMapFragment
        map.getMapAsync(this)
        return binding.root
    }

    override fun onMapReady(googleMap: GoogleMap) {
        if (checkPermission()) {
            return
        }

        val (userLat, userLong) = getMyLocation()
        if (userLat == null || userLong == null)
            return

        val myLocation = LatLng(userLat, userLong)
        googleMap.addMarker(
            MarkerOptions().position(myLocation).title(getString(R.string.my_location))
        )
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 10f))
    }

    private fun checkPermission(): Boolean {
        return ActivityCompat.checkSelfPermission(
            requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    }

    @SuppressLint("MissingPermission")
    private fun getMyLocation(): Pair<Double?, Double?> {
        val locationManager =
            requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val locationProvider = LocationManager.NETWORK_PROVIDER
        val lastKnownLocation =
            locationManager.getLastKnownLocation(locationProvider)
        return lastKnownLocation?.latitude to lastKnownLocation?.longitude
    }
}