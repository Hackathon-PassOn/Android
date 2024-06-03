package com.easyhz.cmc15th_hackathon.presentation.ui.random

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easyhz.cmc15th_hackathon.domain.model.param.RandomItem
import com.easyhz.cmc15th_hackathon.domain.repository.MainRepository
import com.easyhz.cmc15th_hackathon.presentation.ui.RandomResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {


    fun getRandom(textList: List<String>, cate:String, onSuccess: () -> Unit) = viewModelScope.launch {

        if (cate == "F") {
            randomMenu(textList) { onSuccess() }
        } else {
            randomPeople(textList) { onSuccess() }
        }
    }

    fun randomMenu(textList: List<String>, onSuccess: () -> Unit) = viewModelScope.launch {
        mainRepository.randomMenu(RandomItem(textList)).collect() { apiState ->
            apiState.onSuccess {
                RandomResult.title = it.data.title
                RandomResult.content = it.data.content
                onSuccess()
            }
        }
    }

    fun randomPeople(textList: List<String>, onSuccess: () -> Unit) = viewModelScope.launch {
        mainRepository.randomMenu(RandomItem(textList)).collect() { apiState ->
            apiState.onSuccess {
                RandomResult.title = it.data.title
                RandomResult.content = it.data.content
                onSuccess()
            }
        }
    }

}
