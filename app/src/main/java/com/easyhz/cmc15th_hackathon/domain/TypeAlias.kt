package com.easyhz.cmc15th_hackathon.domain

import com.easyhz.cmc15th_hackathon.domain.model.ApiState
import com.easyhz.cmc15th_hackathon.domain.model.response.ResponseWrapper
import retrofit2.Response

typealias NetworkResponse<T> = Response<ResponseWrapper<T>>

typealias ApiWrapper<T> = ApiState<ResponseWrapper<T>>