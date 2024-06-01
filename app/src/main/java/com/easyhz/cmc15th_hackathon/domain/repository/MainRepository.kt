package com.easyhz.cmc15th_hackathon.domain.repository

import com.easyhz.cmc15th_hackathon.domain.ApiWrapper
import com.easyhz.cmc15th_hackathon.domain.model.safeFlow
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    // 지역 내 카테고리 검색 API
    fun searchCategory(): Flow<ApiWrapper<Any>>

    // GPT 떠넘기기 API
    fun helpGPT(): Flow<ApiWrapper<Any>>

    // 룰렛 돌리기 API
    fun runRoulette(): Flow<ApiWrapper<Any>>

    // 퀴즈 랜덤 제공
    fun fetchRandomQuiz(): Flow<ApiWrapper<Any>>

}