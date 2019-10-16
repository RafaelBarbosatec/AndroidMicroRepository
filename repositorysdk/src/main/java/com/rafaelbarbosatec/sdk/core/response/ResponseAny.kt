package com.rafaelbarbosatec.sdk.core.response

import retrofit2.Response

internal const val UNKNOWN_CODE = -1

sealed class ResponseAny<T> {
    companion object {
        fun <T> create(response: Response<T>): ResponseAny<T> {
            return if (response.isSuccessful) {
                val body = response.body()
                if (body == null || response.code() == 204) {
                    ResponseError(-1,"Content Empty")
                } else {
                    ResponseSuccess(body)
                }
            } else {
                ResponseError(
                    response.code(),
                    response.errorBody()?.string() ?: response.message()
                )
            }
        }

        fun <T> create(errorCode: Int, error: Throwable): ResponseError<T> {
            return ResponseError(errorCode, error.message ?: "Unknown Error!")
        }
    }
}

data class ResponseError<T>(val errorCode: Int, val errorMessage: String): ResponseAny<T>()
data class ResponseSuccess<T>(val body: T,var fromDb:Boolean = false): ResponseAny<T>()