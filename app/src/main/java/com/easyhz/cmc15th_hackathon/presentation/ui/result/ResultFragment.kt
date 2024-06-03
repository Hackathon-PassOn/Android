package com.easyhz.cmc15th_hackathon.presentation.ui.result

import android.graphics.Rect
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.easyhz.cmc15th_hackathon.R
import com.easyhz.cmc15th_hackathon.databinding.FragmentResultBinding
import com.easyhz.cmc15th_hackathon.domain.model.param.SearchParam
import com.easyhz.cmc15th_hackathon.presentation.navigation.NavControllerManager
import com.easyhz.cmc15th_hackathon.presentation.ui.SearchParams
import com.easyhz.cmc15th_hackathon.presentation.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment : BaseFragment<FragmentResultBinding>(R.layout.fragment_result) {
    private lateinit var imageViewPagerAdapter: ImageViewPagerAdapter
    private val viewModel: ResultViewModel by viewModels()
    override fun initView() {
        super.initView()
        observeData()
        onClickRandom()
        binding.apply {
            title.text = SearchParams.location
        }
    }

    private fun observeData() {
        viewModel.result.observe(viewLifecycleOwner) {
            imageViewPagerAdapter = ImageViewPagerAdapter(it)
            setUpViewPager()
        }
    }
    private fun setUpViewPager() {
        binding.viewPager.apply {
            adapter = imageViewPagerAdapter
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            clipToPadding = false
            clipChildren = false
            offscreenPageLimit = 3
            getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            val compositePageTransformer = CompositePageTransformer().apply {
                addTransformer(MarginPageTransformer(40))
                addTransformer(ViewPager2.PageTransformer { page, position ->
                    val r = 1 - kotlin.math.abs(position)
                    page.scaleY = 0.90f + r * 0.04f
                })
            }

            setPageTransformer(compositePageTransformer)
            binding.dotsIndicator.setViewPager2(this)

        }


        // registering for page change callback
        binding.viewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback() {

                override fun onPageSelected(position: Int) {
                    super.onPageSelected(position)

                    println("선택")
                }
            }
        )
    }

    private fun onClickRandom() {
        binding.appCompatButton.setOnClickListener {
            NavControllerManager.navigateToRandom()
        }
    }

}