package com.pvasiliev.uplabs.ui

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pvasiliev.uplabs.DividerItemDecoration
import com.pvasiliev.uplabs.HistoryAdapter
import com.pvasiliev.uplabs.R
import kotlinx.android.synthetic.main.view_recent.*

class ExploreFragment : Fragment() {
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
    }
}