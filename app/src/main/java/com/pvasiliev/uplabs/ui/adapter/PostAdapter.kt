package com.pvasiliev.uplabs.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.pvasiliev.uplabs.R
import com.pvasiliev.uplabs.data.models.Post
import kotlinx.android.synthetic.main.item_date.view.*
import kotlinx.android.synthetic.main.item_post.view.*
import org.joda.time.LocalDate

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
            itemView.date_label.text = date.toString()
        }
    }

    class PostVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindPost(post: Post) {
            itemView.post_title.text = post.title
            itemView.post_author.text = post.creator.fullName
        }
    }
}