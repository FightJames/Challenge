package com.techapp.james.scalableimageview

import android.view.MotionEvent
import android.view.View
import android.widget.ImageView

class JamesImageHelper {
    var deltX = 0f
    var deltY = 0f

    companion object {
        val X = "x"
        val Y = "Y"
    }

    constructor()

//    fun setDelt(event: MotionEvent, imageView: ImageView) {
//        deltX = event.rawX - imageView.x
//        deltY = event.rawY - imageView.y
//    }

    fun move(imageView: ImageView, event: MotionEvent): Map<String, Float> {
        var x = 0f
        var y = 0f
        x = event.rawX - deltX
        y = event.rawY - deltY
        if (!isZoomWidthOrHeightOverParent(imageView)) {
            if (isOverLeft(imageView)) {
                x = 0f
            }
            if (isOverTop(imageView)) {
                y = 0f
            }
            if (isOverRight(imageView)) {
                var fixX = (imageView.x + imageView.width) - (imageView.parent as View).width
                x = imageView.x - fixX
            }
            if (isOverBottom(imageView)) {
                var fixY = (imageView.y + imageView.height) - (imageView.parent as View).height
                y = imageView.y - fixY
            }
        }
        var map = HashMap<String, Float>()
        map.put(X, x)
        map.put(Y, y)
        return map
    }

    fun locateImageView(imageView: ImageView) {

        var diagonalPointX = imageView.x + imageView.width
        var diagonalPointY = imageView.y + imageView.height
        var parent = imageView.parent as View
        if ((imageView.x > (parent.x + parent.width) || imageView.y > (parent.y + parent.height)) || (diagonalPointX < 0 || diagonalPointY < 0)) {
            var midPointX = (imageView.x + diagonalPointX) / 2
            var midPointY = (imageView.y + diagonalPointY) / 2
            var midDeltX = midPointX - imageView.x
            var midDeltY = midPointY - imageView.y
            imageView.x = (parent.x + parent.width) / 2 - midDeltX
            imageView.y = (parent.y + parent.height) / 2 - midDeltY
        }
    }

    private fun isZoomWidthOrHeightOverParent(imageView: ImageView): Boolean {
        return (imageView.width > (imageView.parent as View).width && imageView.height > (imageView.parent as View).height)
    }

    private fun isOverLeft(imageView: ImageView): Boolean {
        return imageView.x < 0
    }

    private fun isOverRight(imageView: ImageView): Boolean {
        return (imageView.x + imageView.width) > (imageView.parent as View).width
    }

    private fun isOverTop(imageView: ImageView): Boolean {
        return imageView.y < 0
    }

    private fun isOverBottom(imageView: ImageView): Boolean {
        return (imageView.y + imageView.height) > (imageView.parent as View).height
    }
}