package com.easyhz.cmc15th_hackathon.domain.model.param

data class SearchParam(
    val lat: Double = 37.6823811,
    val lng: Double = 127.232943,
    val local: String = "성신여대입구역",
    val keyword: String = "카페",
)
