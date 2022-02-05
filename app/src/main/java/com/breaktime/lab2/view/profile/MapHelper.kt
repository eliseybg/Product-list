package com.breaktime.lab2.view.profile

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment

fun Fragment.checkPermission(): Boolean {
    return ActivityCompat.checkSelfPermission(
        requireContext(), Manifest.permission.ACCESS_FINE_LOCATION
    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
        requireContext(), Manifest.permission.ACCESS_COARSE_LOCATION
    ) != PackageManager.PERMISSION_GRANTED
}

@SuppressLint("MissingPermission")
fun Fragment.getMyLocation(): Pair<Double?, Double?> {
    val locationManager =
        requireActivity().getSystemService(Context.LOCATION_SERVICE) as LocationManager
    val locationProvider = LocationManager.NETWORK_PROVIDER
    val lastKnownLocation =
        locationManager.getLastKnownLocation(locationProvider)
    return lastKnownLocation?.latitude to lastKnownLocation?.longitude
}