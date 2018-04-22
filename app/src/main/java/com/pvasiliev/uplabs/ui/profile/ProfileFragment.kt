package com.pvasiliev.uplabs.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.pvasiliev.uplabs.R
import com.pvasiliev.uplabs.data.models.Badge
import com.pvasiliev.uplabs.data.tabIdToFragmentKey
import com.pvasiliev.uplabs.ui.BadgeView
import kotlinx.android.synthetic.main.fragment_profile.*
import toothpick.Toothpick

class ProfileFragment : MvpAppCompatFragment(), ProfileView {
    @InjectPresenter
    lateinit var presenter: ProfilePresenter

    @ProvidePresenter
    fun providePresenter(): ProfilePresenter {
        val tabKey = tabIdToFragmentKey(R.layout.fragment_profile)
        return Toothpick.openScope(tabKey).getInstance(ProfilePresenter::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_profile, container, false)

    override fun showBadges(badges: List<Badge>) {
        badges.forEach { container.addView(BadgeView(context, it)) }
    }

    override fun showError(throwable: Throwable) {
    }
}
