package com.easyhz.cmc15th_hackathon.domain.model.response.main

data class Detail(
    val address: String,
    val category: List<String>,
    val homePageUrl: String,
    val local: String,
    val menuInfo: List<String>,
    val openTime: String,
    val status: String,
    val tel: String,
    val thumUrls: List<String>,
    val title: String,
    val x: String,
    val y: String
)