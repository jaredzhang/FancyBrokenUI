package com.jaredzhang.fancybrokenui.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet

class ClipRecyclerView(context: Context?, attrs: AttributeSet?) : RecyclerView(context, attrs) {

    var clipHeight = 0
    var clipPercent = 0f
        set(value) {
            field = value
            if (width > 0) {
                dirthRect.right = width
                dirthRect.bottom = (clipPercent * clipHeight).toInt()
                invalidate(dirthRect)
            }
        }

    val rect = Rect(0, 0, 0, 0)
    val dirthRect = Rect(0, 0, 0, 0)

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
    }

    override fun dispatchDraw(canvas: Canvas) {

        val save = canvas.save()

        rect.top = (clipPercent * clipHeight).toInt()
        rect.right = width
        rect.bottom = height

        canvas.clipRect(rect)

        super.dispatchDraw(canvas)

        canvas.restoreToCount(save)

    }
}