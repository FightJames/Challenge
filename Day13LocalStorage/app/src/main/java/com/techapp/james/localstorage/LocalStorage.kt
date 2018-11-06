package com.techapp.james.localstorage

import android.content.Context
import android.content.SharedPreferences

class LocalStorage {
    private var sharePrefence: SharedPreferences

    private constructor(context: Context) {
        sharePrefence = context.getSharedPreferences(data, Context.MODE_PRIVATE)
    }

    companion object {
        val data = "DATA"
        val nameField = "NAME"
        fun getInstance(context: Context): LocalStorage {
            return LocalStorage(context)
        }
    }

    fun saveData(name: String) {
        sharePrefence.edit()
                .putString(nameField, name)
                .commit()
    }

    fun readData(): String {
        return sharePrefence.getString(nameField, "")
    }
}