package com.techapp.james.imagelist

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var list = ArrayList<Int>()
        list.add(R.drawable.mountain1)
        list.add(R.drawable.mountain2)
        list.add(R.drawable.mountain3)
        list.add(R.drawable.mountain4)
        list.add(R.drawable.mountain5)
        imageList.adapter = ListAdapter(list, this)
        imageList.layoutManager = LinearLayoutManager(this)
        var i = Intent(MediaStore.ACTION_IMAGE_CAPTURE, Uri.parse("dslfj"))
        i.data
        i.extras
        i.extras.get("data")

    }
}
