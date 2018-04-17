package com.pvasiliev.uplabs.ui.adapter

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v7.widget.RecyclerView

class DividerItemDecoration(private val left: Int, private val right: Int) : RecyclerView.ItemDecoration() {
    private val paint = Paint().apply {
        color = Color.parseColor("#E0E0E0")
        strokeWidth = 2.0F
    }

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val paddingLeft = parent.context.resources.getDimension(left)
        val paddingRight = parent.context.resources.getDimension(right)
        (0 until parent.childCount)
                .map { parent.getChildAt(it) }
                .forEach {
                    canvas.drawLine(
                            paddingLeft,
                            it.top.toFloat() + 2.0F,
                            it.width.toFloat() - paddingRight,
                            it.top.toFloat() + 2.0F, paint
                    )
                }
    }
}