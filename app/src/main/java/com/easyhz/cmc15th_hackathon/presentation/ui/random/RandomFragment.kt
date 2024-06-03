package com.easyhz.cmc15th_hackathon.presentation.ui.random

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.easyhz.cmc15th_hackathon.R
import com.easyhz.cmc15th_hackathon.databinding.FragmentRandomBinding
import com.easyhz.cmc15th_hackathon.databinding.FragmentResultBinding
import com.easyhz.cmc15th_hackathon.presentation.navigation.NavControllerManager
import com.easyhz.cmc15th_hackathon.presentation.ui.base.BaseFragment
import com.easyhz.cmc15th_hackathon.presentation.ui.result.ResultViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class RandomFragment : BaseFragment<FragmentRandomBinding>(R.layout.fragment_random) {
    var editTextList = emptyList<EditText>()
    private val viewModel: RandomViewModel by viewModels()
    private val args: RandomFragmentArgs by navArgs()
    override fun initView() {
        super.initView()
        onClickAdd()
        onClickRemove()
        onClickNext()
        binding.apply {
            when(args.cate) {
                "F" -> {
                    textView.text = "음식 종류가 고민되세요? \n랜덤뽑기로 결정해드릴게요!"
                }
                else -> {
                    textView.text = "오늘의 식사내기! \n참여자 이름을 넣어주세요."
                }
            }
        }
    }

    override fun afterViewCreated() {
        super.afterViewCreated()
        editTextList = listOf(
            binding.edit1,
            binding.edit2,
            binding.edit3,
            binding.edit4,
            binding.edit5,
            binding.edit6,
            binding.edit7,
            binding.edit8,
            binding.edit9,
            binding.edit10

        )
    }

    private fun onClickRemove() {
        binding.removeButton.setOnClickListener {
            val currentNumber = binding.number.text.toString().toInt()
            if (currentNumber <= 2) {
                Toast.makeText(requireActivity(), "2칸 이하는 입력할 수 없습니다. ", Toast.LENGTH_SHORT).show()
            } else {
                lifecycleScope.launch {
                    binding.number.text = (currentNumber- 1).toString()
                    setEditTextVisible(binding.number.text.toString().toInt())
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun onClickAdd() {
        binding.addButton.setOnClickListener {
            val currentNumber = binding.number.text.toString().toInt()
            if (currentNumber >= 10) {
                Toast.makeText(requireActivity(), "10칸 이상은 입력할 수 없습니다. ", Toast.LENGTH_SHORT).show()
            } else {
                println(">> ${currentNumber}}")
                lifecycleScope.launch {
                    binding.number.text = (currentNumber + 1).toString()
                    setEditTextVisible(binding.number.text.toString().toInt())
                }
            }
        }
    }

    private fun onClickNext() {
        binding.nextButton.setOnClickListener {
            viewModel.getRandom(checkList(), args.cate) {
                NavControllerManager.navigateToRandomResult(args.cate)
            }
        }
    }

    private fun setEditTextVisible(num: Int) {
        for (i in 0 until  num) { // 0부터 num-1까지 반복
            editTextList[i].visibility = View.VISIBLE
        }
        for (i in num  until 10) { // num부터 8까지 반복
            editTextList[i].visibility = View.GONE
        }
    }

    private fun checkList(): List<String> {
        val numberOfItems = binding.number.text.toString().toIntOrNull() ?: 0
        return editTextList.take(numberOfItems).map { it.text.toString() }
    }

}