package com.pvasiliev.uplabs.ui.main

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.jakewharton.rxbinding2.support.design.widget.RxBottomNavigationView
import com.pvasiliev.uplabs.R
import com.pvasiliev.uplabs.data.tabIdToFragmentKey
import com.pvasiliev.uplabs.ui.BackButtonListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val tabs = hashMapOf<String, Fragment>()

    private val tabKeys = listOf(
            tabIdToFragmentKey(R.layout.fragment_post_list),
            tabIdToFragmentKey(R.layout.fragment_explore),
            tabIdToFragmentKey(R.layout.fragment_profile)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        bottombar.setTextVisibility(false)
        bottombar.enableAnimation(false)
        if (savedInstanceState == null) {
            createTabs()
            supportFragmentManager.beginTransaction()
                    .add(R.id.layout_tab_container, tabs[tabKeys[0]], tabKeys[0])
                    .add(R.id.layout_tab_container, tabs[tabKeys[1]], tabKeys[1])
                    .add(R.id.layout_tab_container, tabs[tabKeys[2]], tabKeys[2])
                    .commit()
        } else {
            findTabs()
        }
        RxBottomNavigationView.itemSelections(bottombar)
                .map(MenuItem::getOrder)
                .subscribe(this::showTab)
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.layout_tab_container)
        if (fragment is BackButtonListener) {
            fragment.onBackPressed()
        } else {
            super.onBackPressed()
        }
    }

    private fun createTabs() {
        tabs[tabKeys[0]] = TabFragment.newInstance(tabKeys[0])
        tabs[tabKeys[1]] = TabFragment.newInstance(tabKeys[1])
        tabs[tabKeys[2]] = TabFragment.newInstance(tabKeys[2])
    }

    private fun findTabs() {
        tabs[tabKeys[0]] = supportFragmentManager.findFragmentByTag(tabKeys[0])
        tabs[tabKeys[1]] = supportFragmentManager.findFragmentByTag(tabKeys[1])
        tabs[tabKeys[2]] = supportFragmentManager.findFragmentByTag(tabKeys[2])
    }

    private fun showTab(tabIndex: Int) {
        supportFragmentManager.beginTransaction()
                .detach(tabs[tabKeys[0]])
                .detach(tabs[tabKeys[1]])
                .detach(tabs[tabKeys[2]])
                .attach(tabs[tabKeys[tabIndex]])
                .commit()
    }
}
