package com.easyhz.cmc15th_hackathon.domain.model

import com.easyhz.cmc15th_hackathon.domain.model.response.ErrorResponse

sealed class ApiState<out T: Any> {
    data class Success<T: Any>(val data: T) : ApiState<T>()

    data class Error(val errorResponse: ErrorResponse) : ApiState<Nothing>()

    data class NotResponse(val message: String?, val exception: Throwable? = null) : ApiState<Nothing>()

    object Loading : ApiState<Nothing>()

    fun onSuccess(onSuccess: (T) -> Unit) {
        if (this is Success) {
            onSuccess(this.data)
        }
    }

    fun onError(onError: (ErrorResponse) -> Unit) {
        if (this is Error) {
            onError(this.errorResponse)
        }

        if (this is NotResponse) {
            onError(ErrorResponse(code = 500, "네트워크 오류"))
        }
    }

    fun onLoading(onLoading: () -> Unit) {
        if (this is Loading) {
            onLoading()
        }
    }

}