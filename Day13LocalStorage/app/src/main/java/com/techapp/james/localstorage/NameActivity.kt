package com.techapp.james.localstorage

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_name.*

class NameActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_name)
        button.setOnClickListener {
            var localStorage = LocalStorage.getInstance(this)
            nameTextView.text = localStorage.readData()
        }
    }
}
