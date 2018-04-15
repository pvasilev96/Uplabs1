package com.pvasiliev.uplabs

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.view_recent.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_search)
        val terms = listOf("Testing message", "Testing message", "Testing message", "Testing message")
        val left = resources.displayMetrics.density.toInt() * 62
        val right = 0
        recycler_view.apply {
            adapter = SearchAdapter(terms)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(left, right))
            setHasFixedSize(true)
        }
    }
}
