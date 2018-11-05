package com.techapp.james.progresscontrol

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var handler: Handler
    var progressFlag = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handler = Handler()

        startBtn.setOnClickListener {
            progressFlag = true
            handler.postDelayed(object : Runnable {
                override fun run() {
                    if (progressFlag) {
                        progressBar.progress = progressBar.progress + 10
                        textView.text = progressBar.progress.toString()
                        if (progressBar.progress != progressBar.max) {
                            handler.postDelayed(this, 1000)
                        }
                    }
                }
            }, 1000)
            pauseBtn.setOnClickListener {
                progressFlag = false
            }
            stopBtn.setOnClickListener {
                progressFlag = false
                progressBar.progress = 0
                textView.text = "0"
            }
        }

    }
}
