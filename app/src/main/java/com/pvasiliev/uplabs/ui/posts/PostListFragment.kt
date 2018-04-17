package com.pvasiliev.uplabs.ui.posts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.pvasiliev.uplabs.R
import com.pvasiliev.uplabs.data.models.Post
import com.pvasiliev.uplabs.data.tabIdToFragmentKey
import toothpick.Toothpick

class PostListFragment : MvpAppCompatFragment(), PostListView {
    @InjectPresenter
    lateinit var presenter: PostListPresenter

    @ProvidePresenter
    fun providePresenter(): PostListPresenter {
        val tabKey = tabIdToFragmentKey(R.layout.fragment_post_list)
        return Toothpick.openScope(tabKey).getInstance(PostListPresenter::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_post_list, container, false)

    override fun showPosts(posts: List<Post>) {
    }

    override fun showError(error: Throwable) {
    }
}