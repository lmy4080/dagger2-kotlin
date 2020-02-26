package com.lmy.dagger2_kotlin.network.main

import com.lmy.dagger2_kotlin.models.Post
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {

    @GET("posts")
    fun getPostsFromUser(
        @Query("userId") id: Int
    ): Flowable<List<Post>>
}