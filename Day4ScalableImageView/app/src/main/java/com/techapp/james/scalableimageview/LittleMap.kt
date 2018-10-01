package com.techapp.james.scalableimageview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View

interface ReDrawListener {
    fun reDraw()
}

class LittleMap : View, ReDrawListener {
    val elementList = ArrayList<JamesImageView>()

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    override fun onDraw(canvas: Canvas) {
        var parent = (parent as View)
        var paint = Paint()
        paint.style = Paint.Style.STROKE
        paint.color = Color.RED
        paint.strokeWidth = 10f

        Log.d("Little", " onDraw")
        elementList.forEach { e ->
            var xRadio = e.x / parent.width
            var yRadio = e.y / parent.height
            var insideX = x + width * xRadio
            var insideY = y + width * yRadio
            canvas.drawCircle(insideX, insideY, 2f, paint)
        }

    }

    override fun reDraw() {
        invalidate()
    }
}