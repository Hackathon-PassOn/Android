package com.easyhz.cmc15th_hackathon.presentation.ui.filter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.easyhz.cmc15th_hackathon.R
import com.easyhz.cmc15th_hackathon.databinding.FragmentFilterBinding
import com.easyhz.cmc15th_hackathon.presentation.navigation.NavControllerManager
import com.easyhz.cmc15th_hackathon.presentation.ui.SearchParams
import com.easyhz.cmc15th_hackathon.presentation.ui.base.BaseFragment

class FilterFragment: BaseFragment<FragmentFilterBinding>(R.layout.fragment_filter) {
    override fun initView() {
        super.initView()
        binding.apply {
            radio.setOnCheckedChangeListener { group, checkedId ->
                println(">>>>>>>>>>>>>> ${checkedId}")
                SearchParams.keyword = when(checkedId) {
                    R.id.check1 -> "한식"
                    R.id.check2 -> "양식"
                    R.id.check3 -> "일식"
                    R.id.check4 -> "중식"
                    else -> { "식당" }
                }

            }

            SearchParams.isOpen = isOpen.isChecked

            appCompatButton.setOnClickListener {
                NavControllerManager.navigateFilterToResult()
            }
        }
    }

}