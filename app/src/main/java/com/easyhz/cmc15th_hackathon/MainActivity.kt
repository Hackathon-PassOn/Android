package com.easyhz.cmc15th_hackathon

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.easyhz.cmc15th_hackathon.databinding.ActivityMainBinding
import com.easyhz.cmc15th_hackathon.presentation.navigation.NavControllerManager
import com.easyhz.cmc15th_hackathon.presentation.util.RequestPermissionUtil
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.AndroidEntryPoint
import java.io.IOException
import java.util.Locale

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
  
    private val permissionUtil: RequestPermissionUtil by lazy {
        RequestPermissionUtil(this)
    }

  override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
//        enableEdgeToEdge()
        initNavControllerManager()
        setContentView(binding.root)
    }


    override fun onStart() {
        super.onStart()
        requestLocation()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == RequestPermissionUtil.REQUEST_LOCATION) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                println("hi")
                getLocation()
            } else {
                // TODO : 다이얼로그로 다이렉트 연결
                Toast.makeText(this, "주소를 가져 올 수 없습니다1", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initNavControllerManager() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        NavControllerManager.init(navHostFragment.navController)
    }

    private fun requestLocation() {
        permissionUtil.requestLocation {
            getLocation()
        }
    }
    @SuppressLint("MissingPermission")
    private fun getLocation() {
        try {
            permissionUtil.getLocation(
                onSuccess = { location ->
                    val address = permissionUtil.getAddress(location.latitude, location.longitude)?.get(0)
                    println("성공 > ${location.latitude}, ${location.longitude} ,, ${address?.thoroughfare}")
                }
            )
        } catch (e: Exception) {
            Toast.makeText(this, "주소를 가져 올 수 없습니다", Toast.LENGTH_SHORT).show()
        }
    }


}