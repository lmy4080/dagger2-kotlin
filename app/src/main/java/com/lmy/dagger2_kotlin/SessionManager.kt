package com.lmy.dagger2_kotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.lmy.dagger2_kotlin.models.User
import com.lmy.dagger2_kotlin.ui.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor() {

    private var cachedUser: MediatorLiveData<AuthResource<User>> = MediatorLiveData()

    fun authenticateWithId(source: LiveData<AuthResource<User>>) {
        cachedUser.value = AuthResource.Loading(null as User?)
        cachedUser.addSource(source) { userAuthResource ->
            cachedUser.value = userAuthResource
            cachedUser.removeSource(source)
        }
    }

    fun logOut() {
        println("logOut: logging out...")
        cachedUser.value = AuthResource.Logout()
    }

    fun getAuthUser(): LiveData<AuthResource<User>> = cachedUser
}