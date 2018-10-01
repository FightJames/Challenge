package com.techapp.james.scalableimageview

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView

class JamesImageView : ImageView {

    companion object {
        val ZOOM = 0
        val DRAGGING = 1
        val NONE = -1
    }

    var mode = NONE

    var reDrawLittleMap: ReDrawListener? = null

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    var priPointDis = 0.00

    var helper = JamesImageHelper()

    override fun onTouchEvent(event: MotionEvent): Boolean {
        reDrawLittleMap?.reDraw()
        when (event.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                if (mode == NONE) {
                    helper.deltX = event.rawX - this.x
                    helper.deltY = event.rawY - this.y
                    mode = DRAGGING
                }
            }
            MotionEvent.ACTION_MOVE -> {
                when (mode) {
                    DRAGGING -> {
                        helper.move(this, event)
                    }
                    ZOOM -> {
                        zoom(event)
                        helper.locateImageView(this)

                    }
                }
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                if (mode != ZOOM) {
                    mode = ZOOM
                    priPointDis = distance(event, priPointDis)
                }
            }
            MotionEvent.ACTION_UP -> {
                Log.d("Image", event.action.toString())
                mode = NONE
            }
        }
        return true
    }

    fun distance(event: MotionEvent, default: Double): Double {
        if (event.pointerCount > 1) {
            return Math.sqrt(Math.pow(event.getX(0).toDouble() - event.getX(1).toDouble(), 2.0) + Math.pow(event.getY(0).toDouble() - event.getY(1).toDouble(), 2.0))
        }
        return default
    }

    fun zoom(event: MotionEvent) {
        var lastpointDis = distance(event, priPointDis)
        var radio = lastpointDis / priPointDis
        zoomCal(radio)
        priPointDis = lastpointDis
    }

    fun zoomCal(radio: Double) {
        var width = this.width * radio
        var height = this.height * radio
        if (width < 300 || height < 300) {
            return
        }
        if (width > (parent as View).width * 3 || height > (parent as View).height * 3) {
            return
        }
        var fixWidth = (width - this.width) / 2
        var fixHeight = (height - this.height) / 2
        var left = (left - fixWidth).toInt()
        var right = left + width
        var top = (top - fixHeight).toInt()
        var bottom = top + height
        var rightInt = if (right % 10.00 == 0.00) right.toInt() else right.toInt() + 1
        var bottomInt = if (bottom % 10.00 == 0.00) bottom.toInt() else bottom.toInt() + 1
        setFrame(left, top, rightInt, bottomInt)
    }
}