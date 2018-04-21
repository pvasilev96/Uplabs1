package com.pvasiliev.uplabs.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pvasiliev.uplabs.R
import com.pvasiliev.uplabs.data.tabIdToFragmentKey
import com.pvasiliev.uplabs.ui.explore.ExploreFragment
import com.pvasiliev.uplabs.ui.Screens
import com.pvasiliev.uplabs.ui.posts.PostListFragment
import com.pvasiliev.uplabs.ui.profile.ProfileFragment
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.SupportAppNavigator
import toothpick.Toothpick
import toothpick.config.Module
import javax.inject.Inject

class TabFragment : Fragment() {
    companion object {
        const val ARGUMENT_TAB_KEY = "tabKey"

        fun newInstance(tabKey: String) =
                TabFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARGUMENT_TAB_KEY, tabKey)
                    }
                }
    }

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private lateinit var tabKey: String

    private val navigator: SupportAppNavigator by lazy { createNavigator() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tabKey = arguments.getString(ARGUMENT_TAB_KEY)
        val scope = Toothpick.openScopes(context.applicationContext, tabKey)
        scope.installModules(object : Module() {
            init {
                val cicerone = Cicerone.create()
                bind(Router::class.java).toInstance(cicerone.router)
                bind(NavigatorHolder::class.java).toInstance(cicerone.navigatorHolder)
            }
        })
        Toothpick.inject(this, scope)
        when (tabKey) {
            tabIdToFragmentKey(R.layout.fragment_post_list) -> router.navigateTo(Screens.POST_LIST_SCREEN)
            tabIdToFragmentKey(R.layout.fragment_explore) -> router.navigateTo(Screens.EXPLORE_SCREEN)
            tabIdToFragmentKey(R.layout.fragment_profile) -> router.navigateTo(Screens.PROFILE_SCREEN)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_tab, container, false)

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        Toothpick.closeScope(tabKey)
    }

    private fun createNavigator() = object : SupportAppNavigator(activity, childFragmentManager, R.id.layout_container) {
        override fun createActivityIntent(context: Context, screenKey: String, data: Any?): Intent? = null

        override fun createFragment(screenKey: String, data: Any?): Fragment? =
                when (screenKey) {
                    Screens.POST_LIST_SCREEN -> PostListFragment()
                    Screens.EXPLORE_SCREEN -> ExploreFragment()
                    Screens.PROFILE_SCREEN -> ProfileFragment()
                    else -> null
                }
    }
}