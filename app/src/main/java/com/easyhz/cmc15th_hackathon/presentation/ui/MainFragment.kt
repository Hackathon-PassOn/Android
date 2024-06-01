package com.easyhz.cmc15th_hackathon.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.easyhz.cmc15th_hackathon.R
import com.easyhz.cmc15th_hackathon.databinding.FragmentMainBinding
import com.easyhz.cmc15th_hackathon.presentation.navigation.NavControllerManager
import com.easyhz.cmc15th_hackathon.presentation.ui.base.BaseFragment

class MainFragment : BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {
    override fun initView() {
        super.initView()
        binding.apply {
            textView.text = "Main Fragment --"
            button.setOnClickListener {
                NavControllerManager.navigateToExample()
            }
        }
    }

}