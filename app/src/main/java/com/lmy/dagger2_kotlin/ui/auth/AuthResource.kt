package com.lmy.dagger2_kotlin.ui.auth

sealed class AuthResource<T>(
    val status: AuthStatus?= null,
    val data: T? = null,
    val message: String? = null
) {

    class Authenticated<T>(data: T?): AuthResource<T>(AuthStatus.AUTHENTICATED, data, null)

    class Error<T>(msg: String, data: T?): AuthResource<T>(AuthStatus.ERROR, data, msg)

    class Loading<T>(data: T?): AuthResource<T>(AuthStatus.LOADING, data, null)

    class Logout<T>: AuthResource<T>(AuthStatus.NOT_AUTHENTICATED, null, null)

    enum class AuthStatus {
        AUTHENTICATED, ERROR, LOADING, NOT_AUTHENTICATED
    }
}