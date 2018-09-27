package com.techapp.james.discount

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        priceEditText.setOnEditorActionListener { v, actionId, event ->

            var text = priceEditText.text.toString()
            if (!text.equals("")) {
                var origin = text.toString().toDouble()
                var discountPrice = origin * (seekBar.progress * 0.01)
                val str: String = String.format("%.2f", discountPrice)
                discountPirceText.text = str
                discountNumberText.text = String.format("%.2f", (origin - discountPrice))
            }

            false
        }
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                discountText.text = "打折($progress%)"
                var text = priceEditText.text.toString()
                if (!text.equals("")) {
                    var origin = text.toString().toDouble()
                    var discountPrice = origin * (progress * 0.01)
                    val str: String = String.format("%.2f", discountPrice)
                    discountPirceText.text = str
                    discountNumberText.text = String.format("%.2f", (origin - discountPrice))
                }

            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })
    }
}
