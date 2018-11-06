package com.techapp.james.localstorage

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var localStorage = LocalStorage.getInstance(this)
        saveBtn.setOnClickListener {
            localStorage.saveData(editText.text.toString())
        }
        nextBtn.setOnClickListener {
            var i = Intent(this, NameActivity::class.java)
            startActivity(i)
        }
    }
}
