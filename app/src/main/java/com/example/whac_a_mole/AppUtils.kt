package com.example.whac_a_mole

import android.content.Context
import androidx.core.content.edit

class AppUtils(context: Context) {

    private val appPreferences by lazy {
        context.getSharedPreferences("app_setting", Context.MODE_PRIVATE)
    }

    fun writeScore(highScore: Int) {
        appPreferences.edit {
            putInt("high_score", highScore)
        }
    }

    fun readScore(): Int {
        return appPreferences.getInt("high_score", 0)
    }
}