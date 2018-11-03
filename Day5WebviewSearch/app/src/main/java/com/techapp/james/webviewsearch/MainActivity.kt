package com.techapp.james.webviewsearch

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.webkit.WebViewClient
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://www.google.com")

        val ime = this@MainActivity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

        searchEditText.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_DONE) {
                    search()
                    ime.hideSoftInputFromWindow(searchEditText.windowToken, 0)
                    return false
                }
                return false
            }
        })

        searchBtn.setOnClickListener {
            search()
            ime.hideSoftInputFromWindow(searchEditText.windowToken, 0)
        }
    }

    private fun search() {
        var target = searchEditText.text
        if (!target.equals("")) {
            webView.loadUrl("https://www.google.com/search?q=$target")
        }
    }
}
