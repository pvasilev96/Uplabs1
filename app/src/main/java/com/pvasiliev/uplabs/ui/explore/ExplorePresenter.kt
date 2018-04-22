package com.pvasiliev.uplabs.ui.explore

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.pvasiliev.uplabs.ui.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class ExplorePresenter @Inject constructor(private val router: Router) : MvpPresenter<ExploreView>() {
    fun onCategoryClicked(category: String) {
        router.navigateTo(Screens.POST_LIST_SCREEN, category.toLowerCase())
    }
}