package com.easyhz.cmc15th_hackathon.data.repository

import com.easyhz.cmc15th_hackathon.data.api.MainService
import com.easyhz.cmc15th_hackathon.domain.ApiWrapper
import com.easyhz.cmc15th_hackathon.domain.model.param.RandomItem
import com.easyhz.cmc15th_hackathon.domain.model.param.SearchParam
import com.easyhz.cmc15th_hackathon.domain.model.response.main.SearchResponse
import com.easyhz.cmc15th_hackathon.domain.model.response.random.RandomResponse
import com.easyhz.cmc15th_hackathon.domain.model.safeFlow
import com.easyhz.cmc15th_hackathon.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainService: MainService
): MainRepository {

    override fun searchCategory(searchParam: SearchParam): Flow<ApiWrapper<List<SearchResponse>>> = safeFlow {
        mainService.searchCategory(lat = searchParam.lat, lng = searchParam.lng, searchParam.local, searchParam.keyword)
    }

    override fun helpGPT(): Flow<ApiWrapper<Any>> = safeFlow {
        mainService.helpGPT()
    }

    override fun runRoulette(): Flow<ApiWrapper<Any>> = safeFlow {
        mainService.runRoulette()
    }

    override fun fetchRandomQuiz(): Flow<ApiWrapper<Any>> = safeFlow {
        mainService.fetchRandomQuiz()
    }

    override fun randomMenu(randomItem: RandomItem): Flow<ApiWrapper<RandomResponse>> = safeFlow {
        mainService.randomMenu(randomItem)
    }

    override fun randomPeople(randomItem: RandomItem): Flow<ApiWrapper<RandomResponse>> = safeFlow {
        mainService.randomPeople(randomItem)
    }
}