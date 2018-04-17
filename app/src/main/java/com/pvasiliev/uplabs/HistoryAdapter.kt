package com.pvasiliev.uplabs

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_history.view.*

class HistoryAdapter(private val items: List<String>) : RecyclerView.Adapter<HistoryAdapter.HistoryVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryVH {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_history, parent, false)
        return HistoryVH(itemView)
    }

    override fun onBindViewHolder(holder: HistoryVH, position: Int) = holder.bindSearchTerm(items[position])

    override fun getItemCount() = items.size

    class HistoryVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindSearchTerm(term: String) {
            itemView.label_search.text = term
        }
    }
}