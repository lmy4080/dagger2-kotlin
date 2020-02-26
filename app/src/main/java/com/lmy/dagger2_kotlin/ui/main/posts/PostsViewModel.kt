package com.lmy.dagger2_kotlin.ui.main.posts

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.lmy.dagger2_kotlin.SessionManager
import com.lmy.dagger2_kotlin.models.Post
import com.lmy.dagger2_kotlin.network.main.MainApi
import com.lmy.dagger2_kotlin.ui.main.Resource
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PostsViewModel @Inject constructor(): ViewModel() {

    @Inject
    lateinit var sessionManager: SessionManager

    @Inject
    lateinit var mainApi: MainApi

    private var posts: MediatorLiveData<Resource<List<Post>>> = MediatorLiveData()

    init {
        println("PostsViewModel is ready...")
    }

    fun observePosts(): LiveData<Resource<List<Post>>> {

        posts = MediatorLiveData()

        posts.value = Resource.Loading(null as List<Post>?)

        val source = LiveDataReactiveStreams.fromPublisher(
            mainApi.getPostsFromUser(sessionManager.getAuthUser().value?.data?.id!!)
                .onErrorReturn {
                    return@onErrorReturn listOf(Post(-1, 0, "", ""))
                }
                .map { posts ->
                    if (posts.isNotEmpty()) {
                        if (posts[0].id == -1) {
                            return@map Resource.Error("Something went wrong", null)
                        }
                    }
                    return@map Resource.Success(posts)
                }
                .subscribeOn(Schedulers.io())
        )

        posts.addSource(source) { listResource ->
            posts.value = listResource as Resource<List<Post>>?
            posts.removeSource(source)
        }

        return posts
    }
}