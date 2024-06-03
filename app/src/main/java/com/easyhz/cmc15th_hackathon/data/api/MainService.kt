package com.easyhz.cmc15th_hackathon.data.api

import android.view.MenuItem
import com.easyhz.cmc15th_hackathon.domain.NetworkResponse
import com.easyhz.cmc15th_hackathon.domain.model.param.RandomItem
import com.easyhz.cmc15th_hackathon.domain.model.response.main.SearchResponse
import com.easyhz.cmc15th_hackathon.domain.model.response.random.RandomResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
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

    @POST("/gpt/random-menu")
    suspend fun randomMenu(
        @Body menuNameList: RandomItem
    ): NetworkResponse<RandomResponse>

    @POST("/gpt/random-payer")
    suspend fun randomPeople(
        @Body menuNameList: RandomItem
    ): NetworkResponse<RandomResponse>
}