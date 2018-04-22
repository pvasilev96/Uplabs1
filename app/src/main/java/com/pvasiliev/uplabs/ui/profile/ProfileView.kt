package com.pvasiliev.uplabs.ui.profile

import com.arellomobile.mvp.MvpView
import com.pvasiliev.uplabs.data.models.Badge

interface ProfileView : MvpView {
    fun showBadges(badges: List<Badge>)
    fun showError(throwable: Throwable)
}