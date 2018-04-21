package com.pvasiliev.uplabs.ui.posts

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.pvasiliev.uplabs.data.models.Post
import com.pvasiliev.uplabs.data.network.UplabsApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class PostListPresenter @Inject constructor(private val api: UplabsApi, private val router: Router) : MvpPresenter<PostListView>() {
    override fun onFirstViewAttach() {
        api.getLatest(0)
                .map { it.groupBy(Post::date) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { postsByDate -> viewState.showPosts(postsByDate) },
                        { error -> viewState.showError(error) }
                )
    }
}