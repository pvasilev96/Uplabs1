package com.pvasiliev.uplabs.ui.posts

import com.arellomobile.mvp.MvpView
import com.pvasiliev.uplabs.data.models.Post
import org.joda.time.LocalDate

interface PostListView : MvpView {
    fun showPosts(postsByDate: Map<LocalDate, List<Post>>)
    fun showError(error: Throwable)
}