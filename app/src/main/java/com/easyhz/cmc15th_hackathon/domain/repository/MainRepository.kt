package com.easyhz.cmc15th_hackathon.domain.repository

import com.easyhz.cmc15th_hackathon.domain.ApiWrapper
import com.easyhz.cmc15th_hackathon.domain.model.param.RandomItem
import com.easyhz.cmc15th_hackathon.domain.model.param.SearchParam
import com.easyhz.cmc15th_hackathon.domain.model.response.main.SearchResponse
import com.easyhz.cmc15th_hackathon.domain.model.response.random.RandomResponse
import kotlinx.coroutines.flow.Flow

interface MainRepository {

    // 지역 내 카테고리 검색 API
    fun searchCategory(searchParam: SearchParam): Flow<ApiWrapper<List<SearchResponse>>>

    // GPT 떠넘기기 API
    fun helpGPT(): Flow<ApiWrapper<Any>>

    // 룰렛 돌리기 API
    fun runRoulette(): Flow<ApiWrapper<Any>>

    // 퀴즈 랜덤 제공
    fun fetchRandomQuiz(): Flow<ApiWrapper<Any>>

    fun randomMenu(randomItem: RandomItem): Flow<ApiWrapper<RandomResponse>>

    fun randomPeople(randomItem: RandomItem): Flow<ApiWrapper<RandomResponse>>

}