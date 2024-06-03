package com.easyhz.cmc15th_hackathon.presentation.ui.result

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.easyhz.cmc15th_hackathon.domain.model.param.SearchParam
import com.easyhz.cmc15th_hackathon.domain.model.response.main.SearchResponse
import com.easyhz.cmc15th_hackathon.domain.repository.MainRepository
import com.easyhz.cmc15th_hackathon.presentation.ui.SearchParams
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    private val mainRepository: MainRepository
): ViewModel() {
    var searchParam = SearchParam(
        lat = SearchParams.lat ?: 37.5453425,
        lng = SearchParams.lng ?: 126.95258369999999,
        keyword = SearchParams.keyword,
        local = SearchParams.location
    )

    private val _result = MutableLiveData<List<SearchResponse>>()
    val result: LiveData<List<SearchResponse>>
        get() = _result

    init {
        searchWithLocation()
    }
    fun searchWithLocation() = viewModelScope.launch {
        println("search -> $searchParam")
        mainRepository.searchCategory(searchParam = searchParam).collect() { apiState ->
            println("here")
            apiState.onSuccess {
                println("성공,,, $it")
                _result.value = it.data.subList(0, 3)
            }
        }

    }
}