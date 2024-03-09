package kr.toru.kotlinflowevent.domain.network

sealed class ApiResponse<T> {
    data class Success<T>(val data: T): ApiResponse<T>()
    data class Failure<T>(val exception: Exception): ApiResponse<T>()
}

fun<T> ApiResponse<T>.onSuccess(action: (T) -> Unit): ApiResponse<T> {
    if (this is ApiResponse.Success) {
        action(data)
    }
    return this
}

fun<T> ApiResponse<T>.onFailure(action: (Exception) -> Unit): ApiResponse<T> {
    if (this is ApiResponse.Failure) {
        action(exception)
    }
    return this
}