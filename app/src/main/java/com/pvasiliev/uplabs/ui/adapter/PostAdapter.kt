package com.pvasiliev.uplabs.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.pvasiliev.uplabs.R
import com.pvasiliev.uplabs.data.models.Post
import kotlinx.android.synthetic.main.item_date.view.*
import kotlinx.android.synthetic.main.item_post.view.*
import org.joda.time.LocalDate
import java.util.*

class PostAdapter(private val postsByDate: Map<LocalDate, List<Post>>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return if (viewType == 0) {
            val itemView = inflater.inflate(R.layout.item_date, parent, false)
            DateVH(itemView)
        } else {
            val itemView = inflater.inflate(R.layout.item_post, parent, false)
            PostVH(itemView)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is DateVH -> {
                val date = getItem(position) as LocalDate
                holder.bindDate(date)
            }
            is PostVH -> {
                val post = getItem(position) as Post
                holder.bindPost(post)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        val items = postsByDate.toList().flatMap { listOf(it.first, *it.second.toTypedArray()) }
        return if (items[position] is LocalDate) {
            0
        } else {
            1
        }
    }

    override fun getItemCount() = postsByDate.keys.size + postsByDate.values.flatten().size

    private fun getItem(position: Int) = postsByDate.toList().flatMap { listOf(it.first, *it.second.toTypedArray()) }[position]

    class DateVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindDate(date: LocalDate) {
            with(itemView) {
                day_of_week_label.text = date.dayOfWeek().getAsText(Locale.ENGLISH)
                date_label.text = date.toString("MMMM d", Locale.ENGLISH)
            }
        }
    }

    class PostVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindPost(post: Post) {
            with(itemView) {
                Glide.with(iv_teaser).load(post.teaser).into(iv_teaser)
                Glide.with(iv_avatar).load(post.creator.avatar).into(iv_avatar)
                tv_title.text = post.title
                tv_description.text = post.creator.fullName
                tv_likes.text = post.upvotes.toString()
            }
        }
    }
}