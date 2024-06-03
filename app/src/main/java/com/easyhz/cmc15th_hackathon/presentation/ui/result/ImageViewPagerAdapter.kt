package com.easyhz.cmc15th_hackathon.presentation.ui.result

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.easyhz.cmc15th_hackathon.R
import com.easyhz.cmc15th_hackathon.databinding.ItemResultImageBinding
import com.easyhz.cmc15th_hackathon.domain.model.response.main.SearchResponse

class ImageViewPagerAdapter(val dataList: List<SearchResponse>) :
    RecyclerView.Adapter<ImageViewPagerAdapter.ViewPagerViewHolder>() {

    inner class ViewPagerViewHolder(val binding: ItemResultImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun setData(data: SearchResponse) {
            Glide.with(binding.root.context)
                .load(data.thumUrl)
                .error(R.drawable.baseline_error_outline_24)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imageView)

            binding.placeTitle.text = data.title
            binding.location.text = data.detail.address.split(" ")[0] + data.detail.address.split(" ")[1]
            binding.backTitle.text = data.title
            binding.backContent.text = "${data.distance} \n\n${data.tel} \n\n${data.detail.category} \n\n${data.openTime} \n\n${data.detail.status}"
            val addressParts = data.detail.address.split(" ")
            if (addressParts.size > 1) {
                binding.location.text = "${addressParts[0]} ${addressParts[1]}"
            } else if (addressParts.size == 1) {
                binding.location.text = addressParts[0]
            } else {
                binding.location.text = data.detail.address
            }

            binding.turn.setOnClickListener {
                flipCard(binding.root, binding.front, binding.back)
            }
        }

    }

    override fun getItemCount(): Int = dataList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {

        val binding = ItemResultImageBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return ViewPagerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {

        holder.setData(dataList[position])
    }

    private fun flipCard(view: View, frontLayout: View, backLayout: View) {
        val scale = view.context.resources.displayMetrics.density
        view.cameraDistance = 8000 * scale

        val flipOut = ObjectAnimator.ofFloat(view, "rotationY", 0f, 90f)
        val flipIn = ObjectAnimator.ofFloat(view, "rotationY", -90f, 0f)

        flipOut.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                if (frontLayout.visibility == View.VISIBLE) {
                    frontLayout.visibility = View.GONE
                    backLayout.visibility = View.VISIBLE
                } else {
                    frontLayout.visibility = View.VISIBLE
                    backLayout.visibility = View.GONE
                }
                flipIn.start()
            }
        })

        flipOut.duration = 250
        flipIn.duration = 250

        flipOut.start()
    }
}