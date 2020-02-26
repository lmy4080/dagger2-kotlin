package com.lmy.dagger2_kotlin.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.lmy.dagger2_kotlin.SessionManager
import com.lmy.dagger2_kotlin.models.User
import com.lmy.dagger2_kotlin.ui.auth.AuthResource
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    val sessionManager: SessionManager
): ViewModel() {

    init {
        println("Viewmodel is ready...")
    }

    fun getAuthenticatedUser(): LiveData<AuthResource<User>> = sessionManager.getAuthUser()
}