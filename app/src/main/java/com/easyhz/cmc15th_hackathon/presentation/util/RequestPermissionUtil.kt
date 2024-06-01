package com.easyhz.cmc15th_hackathon.presentation.util

import android.Manifest
import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.IOException
import java.lang.Exception
import java.util.Locale
import javax.inject.Inject

class RequestPermissionUtil @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        val REQUEST_LOCATION = 1
    }

    private val permissionLocation = arrayOf(
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION
    )

    /** 위치정보 권한 요청**/
    fun requestLocation(earlySet: () -> Unit) {
        if (ActivityCompat.checkSelfPermission(
                context,
                permissionLocation[0]
            ) != PackageManager.PERMISSION_GRANTED
            || ActivityCompat.checkSelfPermission(
                context,
                permissionLocation[1]
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                context as Activity,
                permissionLocation,
                REQUEST_LOCATION
            )
        } else {
            earlySet()
        }
    }

    @SuppressLint("MissingPermission")
    fun getLocation(
        onSuccess: (Location) -> Unit,
    ) {
        val fusedLocationProviderClient =
            LocationServices.getFusedLocationProviderClient(context)

        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener { success: Location? ->
                success?.let { location ->
                    onSuccess(location)
                    val address = getAddress(location.latitude, location.longitude)?.get(0)
                    println("성공 > ${location.latitude}, ${location.longitude} ,, ${address?.thoroughfare}")
                }
            }
            .addOnFailureListener { fail ->
                throw fail
            }
    }

    fun getAddress(lat: Double, lng: Double): List<Address>? {
        lateinit var address: List<Address>

        return try {
            val geocoder = Geocoder(context, Locale.KOREA)
            address = geocoder.getFromLocation(lat, lng, 1) as List<Address>
            address
        } catch (e: IOException) {
            throw e
        }
    }
}
