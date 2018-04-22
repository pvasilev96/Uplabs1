package com.pvasiliev.uplabs.ui

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.LayerDrawable
import android.os.Build
import android.support.text.emoji.EmojiCompat
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.pvasiliev.uplabs.R
import com.pvasiliev.uplabs.data.models.Badge
import kotlinx.android.synthetic.main.view_badge.view.*

class BadgeView(context: Context, badge: Badge) : FrameLayout(context) {
    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.view_badge, this)
        val layer1 = GradientDrawable().apply {
            setColor(Color.parseColor(badge.outerColor))
            setSize(toDp(64), toDp(64))
            shape = GradientDrawable.OVAL
        }
        val layer2 = GradientDrawable().apply {
            setColor(Color.parseColor(badge.innerColor))
            setSize(toDp(56), toDp(56))
            shape = GradientDrawable.OVAL
        }
        if (badge.emoji.contains("ch")) {
            iv_check.setImageResource(R.drawable.ic_check_white_24dp)
        } else {
            btn_emoji.text = EmojiCompat.get().process(badge.emoji)
        }
        val layerDrawable = LayerDrawable(arrayOf(layer1, layer2))
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            layerDrawable.setLayerGravity(1, Gravity.CENTER)
        }
        btn_emoji.background = layerDrawable
    }

    private fun toDp(value: Int): Int {
        return (resources.displayMetrics.density * value).toInt()
    }
}