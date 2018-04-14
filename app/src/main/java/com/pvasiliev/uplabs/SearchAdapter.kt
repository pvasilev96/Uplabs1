package com.pvasiliev.uplabs

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_search.view.*

class SearchAdapter(private val items: List<String>) : RecyclerView.Adapter<SearchAdapter.SearchVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchVH {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_search, parent, false)
        return SearchVH(itemView)
    }

    override fun onBindViewHolder(holder: SearchVH, position: Int) = holder.bindSearchTerm(items[position])

    override fun getItemCount() = items.size

    class SearchVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindSearchTerm(term: String) {
            itemView.label_search.text = term
        }
    }
}