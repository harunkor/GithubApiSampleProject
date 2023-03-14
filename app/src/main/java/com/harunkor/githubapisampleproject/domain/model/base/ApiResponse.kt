package com.harunkor.githubapisampleproject.domain.model.base


sealed class ApiResponse<T> (
    val message: String? = null,
    val data: T? = null
) {
    class Success<T> (data: T?): ApiResponse<T> (data = data)

    class Error<T> (message: String): ApiResponse<T> (message = message)
}