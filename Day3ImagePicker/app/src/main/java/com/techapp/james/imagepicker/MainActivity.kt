package com.techapp.james.imagepicker

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        val ALBUM_CODE = 0
        val CAMERA_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        albumBtn.setOnClickListener {
            var i = Intent(Intent.ACTION_PICK)
            i.type = "image/*"
            startActivityForResult(i, ALBUM_CODE)
        }
        cameraBtn.setOnClickListener {
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                takePictureIntent.resolveActivity(packageManager)?.also {
                    startActivityForResult(takePictureIntent, CAMERA_CODE)
                }
//            i.type = "image/*"
//            startActivity(i)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                ALBUM_CODE -> {
                    var uri = data!!.data
                    imageView.setImageURI(uri)

                }
                CAMERA_CODE -> {
                    var bitmap = data!!.extras.get("data") as Bitmap
                    imageView.setImageBitmap(bitmap)
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
