package com.lmy.dagger2_kotlin

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.lmy.dagger2_kotlin.ui.auth.AuthActivity
import com.lmy.dagger2_kotlin.ui.auth.AuthResource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity: DaggerAppCompatActivity() {

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        sessionManager.getAuthUser().observe(this, Observer {
            it?.let { userAuthResource ->

                when(userAuthResource.status) {

                    AuthResource.AuthStatus.LOADING -> {
                    }

                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        println("MainActivity, LOGIN SUCCESS: " + userAuthResource.data?.email)
                    }

                    AuthResource.AuthStatus.ERROR -> {
                        println("onChanged: " + userAuthResource.message)
                    }

                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                        println("NOT_AUTHENTICATED: ")
                        navLoginScreen()
                    }
                }
            }
        })
    }

    private fun navLoginScreen() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }
}