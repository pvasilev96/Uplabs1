package com.pvasiliev.uplabs.ui.posts

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.pvasiliev.uplabs.data.models.Post
import com.pvasiliev.uplabs.data.network.UplabsApi
import com.pvasiliev.uplabs.di.Wrapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.terrakok.cicerone.Router
import javax.inject.Inject

@InjectViewState
class PostListPresenter @Inject constructor(private val category: Wrapper<String?>, private val api: UplabsApi, private val router: Router) : MvpPresenter<PostListView>() {
    override fun onFirstViewAttach() {
        val requestFactory = if (category.value == null) {
            api.getLatest(0)
        } else {
            api.getByCategory(category.value, 0)
        }
        requestFactory
                .map { it.groupBy(Post::date) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { postsByDate -> viewState.showPosts(postsByDate) },
                        { error -> viewState.showError(error) }
                )
    }
}