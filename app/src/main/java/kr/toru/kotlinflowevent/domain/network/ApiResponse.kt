package kr.toru.kotlinflowevent.domain.network

sealed class ApiResponse<T> {
    data class Success<T>(val data: T): ApiResponse<T>()
    data class Failure<T>(val exception: Exception): ApiResponse<T>()
}