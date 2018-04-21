package com.pvasiliev.uplabs.ui.explore

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.pvasiliev.uplabs.R
import com.pvasiliev.uplabs.data.tabIdToFragmentKey
import com.pvasiliev.uplabs.ui.adapter.DividerItemDecoration
import com.pvasiliev.uplabs.ui.adapter.HistoryAdapter
import kotlinx.android.synthetic.main.view_recent.*
import kotlinx.android.synthetic.main.view_subcategories.*
import toothpick.Toothpick

class ExploreFragment : MvpAppCompatFragment(), ExploreView {

    @InjectPresenter
    lateinit var presenter: ExplorePresenter

    @ProvidePresenter
    fun providePresenter(): ExplorePresenter {
        val tabKey = tabIdToFragmentKey(R.layout.fragment_explore)
        return Toothpick.openScope(tabKey).getInstance(ExplorePresenter::class.java)
    }

    private val history = listOf("Test message", "Test message", "Test message")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_explore, container, false)

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        recycler_view.apply {
            adapter = HistoryAdapter(history)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(R.dimen.history_padding_left, R.dimen.history_padding_right))
            setHasFixedSize(true)
        }
        (0 until layout_subcategories.childCount)
                .map { layout_subcategories.getChildAt(it) }
                .map { it as TextView }
                .forEach { category ->
                    category.setOnClickListener {
                        presenter.onCategoryClicked(category.text.toString())
                    }
                }
    }
}