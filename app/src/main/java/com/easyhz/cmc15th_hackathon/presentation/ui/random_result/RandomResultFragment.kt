package com.easyhz.cmc15th_hackathon.presentation.ui.random_result

import android.annotation.SuppressLint
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.easyhz.cmc15th_hackathon.R
import com.easyhz.cmc15th_hackathon.databinding.FragmentRandomresultBinding
import com.easyhz.cmc15th_hackathon.presentation.navigation.NavControllerManager
import com.easyhz.cmc15th_hackathon.presentation.ui.RandomResult
import com.easyhz.cmc15th_hackathon.presentation.ui.base.BaseFragment
import com.easyhz.cmc15th_hackathon.presentation.ui.random.RandomFragmentArgs

class RandomResultFragment : BaseFragment<FragmentRandomresultBinding>(R.layout.fragment_randomresult) {
    private val args: RandomFragmentArgs by navArgs()
    @SuppressLint("SetTextI18n")
    override fun initView() {
        super.initView()
        val text = if(args.cate == "F") "오늘의 메뉴는 \n${RandomResult.title}!" else "축하합니다! \n${RandomResult.title}님!"
        binding.apply {
            title.text = text
            content.text = RandomResult.content

            retry.setOnClickListener {
                findNavController().navigateUp()
            }

            tohome.setOnClickListener {
                NavControllerManager.navigateToHome()
            }
        }
    }

}