package com.easyhz.cmc15th_hackathon.presentation

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.easyhz.cmc15th_hackathon.MainActivity
import com.easyhz.cmc15th_hackathon.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Delay to show the splash screen
        Handler(Looper.getMainLooper()).postDelayed({
            // Start your main activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Finish splash activity
        }, 3000) // 3 seconds delay
    }
}