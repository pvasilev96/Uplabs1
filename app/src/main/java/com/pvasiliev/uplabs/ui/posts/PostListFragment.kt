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
import com.pvasiliev.uplabs.di.Wrapper
import com.pvasiliev.uplabs.ui.adapter.MarginItemDecoration
import com.pvasiliev.uplabs.ui.adapter.PostAdapter
import kotlinx.android.synthetic.main.fragment_post_list.*
import org.joda.time.LocalDate
import toothpick.Toothpick
import toothpick.config.Module

class PostListFragment : MvpAppCompatFragment(), PostListView {
    companion object {
        const val ARGUMENT_CATEGORY = "category"

        fun newInstance(category: String?) =
                PostListFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARGUMENT_CATEGORY, category)
                    }
                }
    }

    @InjectPresenter
    lateinit var presenter: PostListPresenter

    @ProvidePresenter
    fun providePresenter(): PostListPresenter {
        val category = arguments.getString(ARGUMENT_CATEGORY)
        val tabKey = if (category == null) {
            tabIdToFragmentKey(R.layout.fragment_post_list)
        } else {
            tabIdToFragmentKey(R.layout.fragment_explore)
        }
        val scope = Toothpick.openScope(tabKey)
        scope.installModules(object : Module() {
            init {
                bind(Wrapper::class.java).toInstance(Wrapper(category))
            }
        })
        return scope.getInstance(PostListPresenter::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_post_list, container, false)

    override fun showPosts(postsByDate: Map<LocalDate, List<Post>>) {
        recycler_view.apply {
            adapter = PostAdapter(postsByDate)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(MarginItemDecoration(R.dimen.post_margin_horizontal, R.dimen.post_margin_vertical, R.dimen.post_margin_horizontal))
        }
    }

    override fun showError(error: Throwable) {
    }
}