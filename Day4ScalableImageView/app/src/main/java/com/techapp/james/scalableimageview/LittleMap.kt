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
//        canvas.drawLine(0f, 0f, 300f, 300f, paint)

        elementList.forEach { e ->

            var xRadio = e.x / parent.width
            var yRadio = e.y / parent.height
            var insideX = width * xRadio
            var insideY = height * yRadio
            Log.d("Little", " onDraw $insideX $insideY")
            canvas.drawCircle(insideX, insideY, 10f, paint)
        }

    }

    override fun reDraw() {
        invalidate()
    }
}