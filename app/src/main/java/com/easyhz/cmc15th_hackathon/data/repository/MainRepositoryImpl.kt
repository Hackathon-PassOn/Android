package com.easyhz.cmc15th_hackathon.data.repository

import com.easyhz.cmc15th_hackathon.data.api.MainService
import com.easyhz.cmc15th_hackathon.domain.ApiWrapper
import com.easyhz.cmc15th_hackathon.domain.model.safeFlow
import com.easyhz.cmc15th_hackathon.domain.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(
    private val mainService: MainService
): MainRepository {

    override fun searchCategory(): Flow<ApiWrapper<Any>> = safeFlow {
        mainService.searchCategory(lat = 0.0, lng = 0.0, "", "")
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

}