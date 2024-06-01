package com.easyhz.cmc15th_hackathon.data.api

import com.easyhz.cmc15th_hackathon.domain.NetworkResponse
import com.easyhz.cmc15th_hackathon.domain.model.response.main.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MainService {

    // 지역 내 카테고리 검색 API
    @GET("/search")
    suspend fun searchCategory(
        @Query("x") lat: Double,
        @Query("y") lng: Double,
        @Query("local") local: String,
        @Query("keyword") keyword: String,
    ): NetworkResponse<List<SearchResponse>>

    // GPT 떠넘기기 API
    @GET("")
    suspend fun helpGPT(): NetworkResponse<Any>

    // 룰렛 돌리기 API
    @GET("")
    suspend fun runRoulette(): NetworkResponse<Any>

    // 퀴즈 랜덤 제공
    @GET("")
    suspend fun fetchRandomQuiz(): NetworkResponse<Any>
}