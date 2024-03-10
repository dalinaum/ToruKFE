package kr.toru.kotlinflowevent.domain.network

sealed class ApiResponse<T> {
    data class Success<T>(val data: T): ApiResponse<T>()
    data class Failure<T>(val exception: Exception): ApiResponse<T>()
}

// 안 쓰는 것은 지우는게 나은 듯.
fun<T> ApiResponse<T>.onSuccess(action: (T) -> Unit): ApiResponse<T> {
    if (this is ApiResponse.Success) {
        action(data)
    }
    return this
}

// 안 쓰는 것은 지우는게 나은 듯.

fun<T> ApiResponse<T>.onFailure(action: (Exception) -> Unit): ApiResponse<T> {
    if (this is ApiResponse.Failure) {
        action(exception)
    }
    return this
}