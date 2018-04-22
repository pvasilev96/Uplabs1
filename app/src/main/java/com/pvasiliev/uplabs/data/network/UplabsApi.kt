package com.pvasiliev.uplabs.data.network

import com.pvasiliev.uplabs.data.models.Follow
import com.pvasiliev.uplabs.data.models.Post
import com.pvasiliev.uplabs.data.models.Upvote
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface UplabsApi {
    @GET("/all.json")
    fun getLatest(@Query("days_ago") ago: Int): Single<List<Post>>

    @GET("/posts/c/all/resources/{category}.json")
    fun getByCategory(@Path("category") category: String, @Query("page") page: Int): Single<List<Post>>

    @GET("/{nickname}")
    fun getBadges(@Path("nickname") nickname: String): Single<String>

    @PUT("/posts/{postId}/upvote")
    fun upvote(@Path("postId") postId: Int): Single<Upvote>

    @PUT("/posts/{postId}/downvote")
    fun downvote(@Path("postId") postId: Int): Single<Upvote>

    @PUT("/users/{nickname}/follow")
    fun follow(@Path("nickname") nickname: String): Single<Follow>

    @PUT("/users/{nickname}/unfollow")
    fun unfollow(@Path("nickname") nickname: String): Single<Follow>
}