package com.easyhz.cmc15th_hackathon.presentation.ui

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.NavHostFragment
import com.easyhz.cmc15th_hackathon.R
import com.easyhz.cmc15th_hackathon.databinding.FragmentMainBinding
import com.easyhz.cmc15th_hackathon.presentation.navigation.NavControllerManager
import com.easyhz.cmc15th_hackathon.presentation.ui.base.BaseFragment
import com.easyhz.cmc15th_hackathon.presentation.util.RequestPermissionUtil

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    private val permissionUtil: RequestPermissionUtil by lazy {
        RequestPermissionUtil(requireContext())
    }

    override fun onStart() {
        super.onStart()
        requestLocation()
    }

    override fun initView() {
        super.initView()
        binding.apply {
            /*textView.text = "Main Fragment --"
            button.setOnClickListener {
                NavControllerManager.navigateToExample()
            }*/
//            textView.text = "Main Fragment --"
//            button.setOnClickListener {
//                NavControllerManager.navigateToExample()
//            }
            meal.setOnClickListener {
                SearchParams.keyword = "식당"
                NavControllerManager.navigateToFilter()
            }

            cafe.setOnClickListener {
                SearchParams.keyword = "카페"
                NavControllerManager.navigateToResult()
            }

            bar.setOnClickListener {
                SearchParams.keyword = "술집"
                NavControllerManager.navigateToFilter()
            }

            random.setOnClickListener {
                println("click babay")
                NavControllerManager.navigateMainToRandom()
            }
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == RequestPermissionUtil.REQUEST_LOCATION) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLocation()
            } else {
                Toast.makeText(requireContext(), "주소를 가져 올 수 없습니다1", Toast.LENGTH_SHORT).show()
            }
        }
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
                    binding.address.text = address?.thoroughfare
                    SearchParams.location = address?.thoroughfare ?: ""
                    SearchParams.lat = address?.latitude
                    SearchParams.lng = address?.longitude
                    activity?.runOnUiThread {
                        binding.address.text =  address?.thoroughfare ?: ""
                        // 강제로 화면 다시 그리기
                        binding.address.invalidate()
                        binding.address.requestLayout()
                    }
                    println(" >> ${SearchParams.lat} ${SearchParams.lng}")
                }
            )
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "주소를 가져 올 수 없습니다", Toast.LENGTH_SHORT).show()
        }
    }


}