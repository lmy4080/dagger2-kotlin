package com.lmy.dagger2_kotlin.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import com.lmy.dagger2_kotlin.R
import com.lmy.dagger2_kotlin.models.User
import com.lmy.dagger2_kotlin.ui.main.MainActivity
import com.lmy.dagger2_kotlin.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity(), View.OnClickListener {

    private lateinit var viewModel: AuthViewModel

    private lateinit var progressBar: ProgressBar

    private lateinit var userId: EditText

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        userId = findViewById(R.id.user_id_input)
        progressBar = findViewById(R.id.progress_bar)
        findViewById<Button>(R.id.login_button).setOnClickListener(this)

        viewModel = ViewModelProviders.of(this, providerFactory)[AuthViewModel::class.java]

        setLogo()

        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.observeAuthState().observe(this, Observer {
            it?.let { userAuthResource ->

                when(userAuthResource.status) {

                    AuthResource.AuthStatus.LOADING -> {
                        showProgressBar(true)
                    }

                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        showProgressBar(false)
                        println("AuthActivity, LOGIN SUCCESS: " + userAuthResource.data?.email)
                        onLoginSuccess()
                    }

                    AuthResource.AuthStatus.ERROR -> {
                        showProgressBar(false)
                        Toast.makeText(
                            this@AuthActivity,
                            userAuthResource.message + "\nDid you enter a number between 1 and 10?",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                        showProgressBar(false)
                    }
                }
            }
        })
    }

    private fun onLoginSuccess() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun showProgressBar(isVisible: Boolean) {
        progressBar.visibility = if(isVisible) View.VISIBLE else View.GONE
    }

    private fun setLogo() {
        requestManager
            .load(logo)
            .into(findViewById(R.id.login_logo))
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.login_button -> attemptLogin()
        }
    }

    private fun attemptLogin() {
        if (TextUtils.isEmpty(userId.text.toString())) {
            return
        }
        viewModel.authenticateWithId(Integer.parseInt(userId.text.toString()))
    }
}
