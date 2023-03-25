package com.refing.tmdbbrowserapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.refing.tmdbbrowserapp.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    val time : Long = 1000
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setTheme(R.style.SplashTheme)
        binding.textLogo.text = resources.getString(R.string.logo)
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, time)

    }
}