package com.example.moviegrid.prefs

import android.content.SharedPreferences
import com.example.moviegrid.extentions.parseDouble
import javax.inject.Inject

class PreferencesHelper @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {

    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun <T> store(
        key: String,
        value: T
    ) {

        if (key.isBlank()) {
            throw NullPointerException("Key must not be null! (key = $key), (value = $value)")
        }

        when (value) {
            is String -> editor.putString(key, value)
            is Int -> editor.putInt(key, value as Int)
            is Float -> editor.putFloat(key, value as Float)
            is Long -> editor.putLong(key, value as Long)
            is Boolean -> editor.putBoolean(key, value as Boolean)
            else -> editor.putString(key, value as String)
        }

        editor.apply()
    }

    fun getString(key: String): String? =
        sharedPreferences.getString(key, "")

    fun getInt(key: String): Int = sharedPreferences.getInt(key, 0)

    fun getDoubleFromString(key: String): Double? =
        sharedPreferences.getString(key, "0.0").parseDouble()

    fun getBoolean(key: String): Boolean =
        sharedPreferences.getBoolean(key, false)

    fun clearAll() = sharedPreferences.edit().clear().apply()

}