package com.techapp.james.scalableimageview

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.widget.ImageView

class JamesImageView : ImageView {

    companion object {
        val ZOOM = 0
        val DRAGGING = 1
        val NONE = -1
    }

    var mode = NONE

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    var deltx = 0f
    var delty = 0f
    var priPointDis = 0.00
    override fun onTouchEvent(event: MotionEvent): Boolean {
//        Log.d("Image ", event.pointerCount.toString())
        when (event.action and MotionEvent.ACTION_MASK) {
            MotionEvent.ACTION_DOWN -> {
                deltx = event.rawX - this.x
                delty = event.rawY - this.y
                mode = DRAGGING
            }
            MotionEvent.ACTION_MOVE -> {
                when (mode) {
                    DRAGGING -> {
                        this.x = event.rawX - deltx
                        this.y = event.rawY - delty
                    }
                    ZOOM -> {
                        zoom(event)
                    }
                }
            }
            MotionEvent.ACTION_POINTER_DOWN -> {
                if (mode != ZOOM) {
                    mode = ZOOM
                    priPointDis = distance(event, priPointDis)
                }
//                Log.d("JamesImage ", "Action Pointer Down")
            }
            MotionEvent.ACTION_POINTER_UP -> {
                mode = DRAGGING
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
        if (priPointDis > lastpointDis) {
            zoomOut(radio)
        } else {
            zoomIn(radio)
//            Log.d("Image ", "Zoom In")
        }
        priPointDis = lastpointDis
    }

    fun zoomIn(radio: Double) {
        var increaseWidth = (this.width * radio - this.width) / 2
        var increaseHeight = (this.height * radio - this.height) / 2
//        Log.d("Zoom In ", "$increaseWidth $increaseHeight $radio")
        setFrame((left - increaseWidth).toInt(), (top - increaseHeight).toInt(), (right + increaseWidth).toInt(), (bottom + increaseHeight).toInt())
    }

    fun zoomOut(radio: Double) {
        var decreaseWidth = (this.width * radio - this.width) / 2
        var decreaseHeight = (this.height * radio - this.height) / 2
//        Log.d("Zoom In ", "$decreaseWidth $decreaseHeight $radio")
        setFrame((left - decreaseWidth).toInt(), (top - decreaseHeight).toInt(), (right + decreaseWidth).toInt(), (bottom + decreaseHeight).toInt())
    }
}