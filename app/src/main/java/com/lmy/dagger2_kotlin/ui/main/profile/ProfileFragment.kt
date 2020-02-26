package com.lmy.dagger2_kotlin.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.lmy.dagger2_kotlin.R
import com.lmy.dagger2_kotlin.di.ViewModelFactoryModule
import com.lmy.dagger2_kotlin.models.User
import com.lmy.dagger2_kotlin.ui.auth.AuthResource
import com.lmy.dagger2_kotlin.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class ProfileFragment: DaggerFragment() {

    private lateinit var viewModel: ProfileViewModel
    private lateinit var email : TextView
    private lateinit var username : TextView
    private lateinit var website : TextView

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        println("onCreateView called")
        Toast.makeText(activity, "Profile Fragment", Toast.LENGTH_SHORT).show()
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        println("onViewCreated called")
        email = view.findViewById(R.id.email)
        username = view.findViewById(R.id.username)
        website = view.findViewById(R.id.website)

        viewModel = ViewModelProviders.of(this, providerFactory)[ProfileViewModel::class.java]

        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.getAuthenticatedUser().removeObservers(viewLifecycleOwner)
        viewModel.getAuthenticatedUser().observe(viewLifecycleOwner, Observer {
            it?.let { userAuthResource ->

                when(userAuthResource.status) {

                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        setUserDetails(userAuthResource.data)
                    }

                    AuthResource.AuthStatus.ERROR -> {
                        setErrorDetails(userAuthResource.message)
                    }
                    else -> {

                    }
                }
            }
        })
    }

    private fun setUserDetails(data: User?) {
        email.text = data?.email
        username.text = data?.username
        website.text = data?.website
    }

    private fun setErrorDetails(message: String?) {
        email.text = message
        username.text = "error"
        website.text = "error"
    }
}