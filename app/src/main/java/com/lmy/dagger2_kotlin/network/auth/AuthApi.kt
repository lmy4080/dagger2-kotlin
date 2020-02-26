package com.lmy.dagger2_kotlin.network.auth

import com.lmy.dagger2_kotlin.models.User
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {

    @GET("users/{id}")
    fun getUser(
        @Path("id") id: Int
    ): Flowable<User>
}