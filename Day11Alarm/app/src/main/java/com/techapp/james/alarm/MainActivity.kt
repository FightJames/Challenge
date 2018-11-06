package com.techapp.james.alarm

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.RequiresApi
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var cal = Calendar.getInstance()

        dateEditText.setOnClickListener {
            var year = cal.get(Calendar.YEAR)
            var month = cal.get(Calendar.MONTH)
            var day = cal.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(this, object : DatePickerDialog.OnDateSetListener {
                override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
                    cal.set(Calendar.YEAR, year)
                    cal.set(Calendar.MONTH, month)
                    cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                    var date = year.toString() + "-" + month.toString() + "-" + dayOfMonth.toString()
                    dateEditText.setText(date)
                }
            }, year, month, day).show()

        }
        timeEditText.setOnClickListener {

            var hourOfDay = cal.get(Calendar.HOUR_OF_DAY)
            var minute = cal.get(Calendar.MINUTE)
            TimePickerDialog(this, object : TimePickerDialog.OnTimeSetListener {
                override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
                    var time = hourOfDay.toString() + ":" + minute.toString()

                    cal.set(Calendar.HOUR_OF_DAY, hourOfDay)
                    cal.set(Calendar.MINUTE, minute)
                    timeEditText.setText(time)
                }
            }, hourOfDay, minute, true).show()
        }
        goBtn.setOnClickListener {
            val time = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.TAIWAN).format(cal.time)
            AlertDialog.Builder(this).setTitle("Time").setMessage(time).setPositiveButton("Ok", { dialog, which ->
                Toast.makeText(this, "Ok", Toast.LENGTH_LONG).show()
            })
                    .setNegativeButton("Cancel", { dialog, which ->

                        Toast.makeText(this, "Cancel", Toast.LENGTH_LONG).show()
                    }).create().show()
        }
    }
}
