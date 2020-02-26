package com.lmy.dagger2_kotlin.ui.main.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.lmy.dagger2_kotlin.R
import com.lmy.dagger2_kotlin.ui.main.Resource
import com.lmy.dagger2_kotlin.util.VerticalSpacingItemDecoration
import com.lmy.dagger2_kotlin.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PostsFragment: DaggerFragment() {

    private lateinit var viewModel: PostsViewModel
    private lateinit var recyclerView: RecyclerView

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    @Inject
    lateinit var adapter: PostsRecyclerAdapter

    @Inject
    lateinit var verticalSpacingItemDecoration: VerticalSpacingItemDecoration

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        recyclerView = view.findViewById(R.id.recycler_view)

        viewModel = ViewModelProviders.of(this, providerFactory)[PostsViewModel::class.java]

        initRecyclerView()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.observePosts().removeObservers(viewLifecycleOwner)
        viewModel.observePosts().observe(viewLifecycleOwner, Observer { listResource ->
            if(listResource != null) {
                println("${listResource.data}")

                when(listResource.status) {

                    Resource.Status.LOADING -> {
                        println("LOADING ...")
                    }

                    Resource.Status.SUCCESS -> {
                        println("SUCCESS ...")
                        adapter.setPosts(listResource.data!!)
                    }

                    Resource.Status.ERROR -> {
                        println("ERROR ... " + listResource.message)
                    }
                }
            }
        })
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.addItemDecoration(verticalSpacingItemDecoration)
        recyclerView.adapter = adapter
    }
}