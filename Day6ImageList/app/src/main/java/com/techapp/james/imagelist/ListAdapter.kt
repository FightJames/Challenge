package com.techapp.james.imagelist

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.view.menu.MenuView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_layout.view.*

class ListAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private var list: ArrayList<Int>
    private var context: Context

    constructor(list: ArrayList<Int>, context: Context) {
        this.list = list
        this.context = context.applicationContext
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var v = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return MyViewHolder(v)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(viewHolder: RecyclerView.ViewHolder, position: Int) {
        var bitmap = decodeBitmap(list[position])
        viewHolder.itemView.imageView.setImageBitmap(bitmap)
        viewHolder.itemView.textView.text = "Mountain $position"
    }

    fun decodeBitmap(resId: Int): Bitmap {
        var opt = BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        var intputStream = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(intputStream, null, opt);
    }

    class MyViewHolder : RecyclerView.ViewHolder {
        constructor(itemView: View) : super(itemView) {
        }
    }
}