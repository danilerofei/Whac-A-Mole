package com.example.whac_a_mole

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.whac_a_mole.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val appUtils by lazy {
        AppUtils(this@MainActivity)
    }

    var highScore: Int
        get() = appUtils.readScore()
        set(value) {
            appUtils.writeScore(value)
        }

    var gameScore: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onBackPressed() {
        finish()
    }
}