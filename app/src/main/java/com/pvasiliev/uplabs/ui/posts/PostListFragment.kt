package com.pvasiliev.uplabs.ui.posts

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.pvasiliev.uplabs.R
import com.pvasiliev.uplabs.data.models.Post
import com.pvasiliev.uplabs.data.tabIdToFragmentKey
import com.pvasiliev.uplabs.ui.adapter.PostAdapter
import kotlinx.android.synthetic.main.fragment_post_list.*
import org.joda.time.LocalDate
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

    override fun showPosts(postsByDate: Map<LocalDate, List<Post>>) {
        recycler_view.adapter = PostAdapter(postsByDate)
        recycler_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    override fun showError(error: Throwable) {
    }
}