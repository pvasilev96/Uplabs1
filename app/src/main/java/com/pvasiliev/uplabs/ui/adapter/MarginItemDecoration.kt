package com.pvasiliev.uplabs.ui.adapter

import android.content.res.Resources
import android.graphics.Rect
import android.support.annotation.DimenRes
import android.support.v7.widget.RecyclerView
import android.view.View

class MarginItemDecoration(@field:DimenRes
                           private val marginLeft: Int,
                           @field:DimenRes
                           private val marginTop: Int,
                           @field:DimenRes
                           private val marginRight: Int,
                           @field:DimenRes
                           private val marginBottom: Int,
                           @field:DimenRes
                           private val marginBetween: Int) : RecyclerView.ItemDecoration() {

    constructor(@DimenRes marginHorizontal: Int,
                @DimenRes marginVertical: Int,
                @DimenRes marginBetween: Int) : this(marginHorizontal, marginVertical, marginHorizontal, marginVertical, marginBetween) {
    }

    override fun getItemOffsets(outRect: Rect,
                                view: View,
                                parent: RecyclerView,
                                state: RecyclerView.State?) {
        val resources = view.resources
        val pxLeft = getPixelSize(resources, marginLeft)
        val pxTop = getPixelSize(resources, marginTop)
        val pxRight = getPixelSize(resources, marginRight)
        val pxBottom = getPixelSize(resources, marginBottom)
        val pxBetween = getPixelSize(resources, marginBetween) / 2

        val count = parent.adapter.itemCount - 1
        val position = parent.getChildAdapterPosition(view)
        val firstPosition = position == 0
        val lastPosition = position == count
        when {
            firstPosition -> {
                outRect.top = pxTop
                outRect.bottom = pxBetween
            }
            lastPosition -> {
                outRect.top = pxBetween
                outRect.bottom = pxBottom
            }
            else -> {
                outRect.top = pxBetween
                outRect.bottom = pxBetween
            }
        }
        outRect.left = pxLeft
        outRect.right = pxRight
    }

    private fun getPixelSize(resources: Resources, @DimenRes margin: Int): Int {
        return if (margin == 0) 0 else resources.getDimensionPixelSize(margin)
    }
}