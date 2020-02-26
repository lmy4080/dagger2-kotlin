package com.lmy.dagger2_kotlin.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.lmy.dagger2_kotlin.SessionManager
import com.lmy.dagger2_kotlin.models.User
import com.lmy.dagger2_kotlin.network.auth.AuthApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel
    @Inject constructor(
        val authApi: AuthApi,
        val sessionManager: SessionManager
    ): ViewModel() {

    init {
        println("AuthViewModel is working...")
    }

    fun authenticateWithId(userId: Int) {
        println("authenticateWithId, attempting to login")
        sessionManager.authenticateWithId(queryUserId(userId))
    }


    // Flowable -> LiveData
    // 응답 객체를 AuthResource 로 Wrapping 함
    private fun queryUserId(userId: Int): LiveData<AuthResource<User>>
        = LiveDataReactiveStreams.fromPublisher(
            authApi.getUser(userId)

            .onErrorReturn {
                return@onErrorReturn User(-1,"","","")
            }

            .map { user ->
                if(user.id == -1) {
                    return@map AuthResource.Error("Could not authenticate", null as User?)
                }
                return@map AuthResource.Authenticated(user)
            }

            .subscribeOn(Schedulers.io()))

    fun observeAuthState(): LiveData<AuthResource<User>>
        = sessionManager.getAuthUser()
}