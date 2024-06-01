package com.easyhz.cmc15th_hackathon.domain.model.response.main

data class SearchResponse(
    val detail: Detail,
    val distance: String,
    val openTime: String,
    val tel: String,
    val thumUrl: String,
    val title: String
)