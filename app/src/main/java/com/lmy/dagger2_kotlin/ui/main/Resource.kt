package com.lmy.dagger2_kotlin.ui.main

sealed class Resource<T>(
    val status: Status?= null,
    val data: T?= null,
    val message: String?= null
) {

    class Success<T>(data: T?): Resource<T>(Status.SUCCESS, data, null)

    class Error<T>(message: String?, data: T?): Resource<T>(Status.ERROR, data, message)

    class Loading<T>(data: T?): Resource<T>(Status.LOADING, data, null)

    enum class Status { SUCCESS, ERROR, LOADING }
}