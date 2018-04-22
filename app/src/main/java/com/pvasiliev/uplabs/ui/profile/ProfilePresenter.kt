package com.pvasiliev.uplabs.ui.profile

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.pvasiliev.uplabs.data.network.UplabsApi
import com.pvasiliev.uplabs.data.parseBadges
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@InjectViewState
class ProfilePresenter @Inject constructor(private val api: UplabsApi) : MvpPresenter<ProfileView>() {
    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        api.getBadges("matt")
                .map { parseBadges(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.showBadges(it)
                }, {
                    viewState.showError(it)
                })
    }
}