package com.pvasiliev.uplabs.ui.posts

import com.arellomobile.mvp.MvpView
import com.pvasiliev.uplabs.data.models.Post

interface PostListView : MvpView {
    fun showPosts(posts: List<Post>)
    fun showError(error: Throwable)
}