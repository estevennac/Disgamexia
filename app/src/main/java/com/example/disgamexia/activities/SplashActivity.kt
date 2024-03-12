package com.example.disgamexia.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.disgamexia.R

class SplashActivity : AppCompatActivity() {
    private lateinit var splashImage:ImageView
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashImage = findViewById(R.id.inicioView)
        animateZoomOut()
    }

    private fun animateZoomOut(){
        splashImage.animate()
            .scaleX(0.4f)
            .scaleY(0.4f)
            .setDuration(1000)
            .withEndAction{
                animateZoomIn()
            }
            .start()
    }

    private fun animateZoomIn(){
        splashImage.animate()
            .scaleX(30f)
            .scaleY(30f)
            .setDuration(500)
            .withEndAction{
                startNewActivity()
            }
            .start()
    }

    private fun startNewActivity(){
        startActivity(Intent(this, MainActivity::class.java));
        finish()
    }
}