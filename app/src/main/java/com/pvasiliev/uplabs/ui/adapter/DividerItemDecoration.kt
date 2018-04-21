package com.pvasiliev.uplabs.ui.adapter

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.support.v7.widget.RecyclerView

class DividerItemDecoration(private val left: Int, private val right: Int) : RecyclerView.ItemDecoration() {
    private val strokeWidth = 2.0F

    private val paint = Paint().apply {
        color = Color.parseColor("#E0E0E0")
        strokeWidth = this@DividerItemDecoration.strokeWidth
    }

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val paddingLeft = parent.context.resources.getDimensionPixelSize(left)
        val paddingRight = parent.context.resources.getDimensionPixelSize(right)
        (0 until parent.childCount)
                .map { parent.getChildAt(it) }
                .forEach {
                    canvas.drawLine(
                            paddingLeft.toFloat(),
                            it.top.toFloat() + strokeWidth,
                            it.width.toFloat() - paddingRight,
                            it.top.toFloat() + strokeWidth, paint
                    )
                }
    }
}