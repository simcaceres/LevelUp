package com.levelupgamer.view

import android.content.Intent
import android.os.Bundle
import android.view.animation.*
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.levelupgamer.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val logo: ImageView = findViewById(R.id.logoImage)
        val titulo: TextView = findViewById(R.id.txtTitulo)

        // 🔥 Combinación de animaciones (Fade In + Escala + Rotación)
        val fadeIn = AlphaAnimation(0f, 1f).apply {
            duration = 1500
            fillAfter = true
        }

        val scaleUp = ScaleAnimation(
            0.5f, 1f, 0.5f, 1f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        ).apply {
            duration = 1500
            interpolator = OvershootInterpolator()
        }

        val rotate = RotateAnimation(
            0f, 360f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        ).apply {
            duration = 1200
            interpolator = AccelerateDecelerateInterpolator()
        }

        // 🎮 Ejecutar animaciones juntas
        val animSet = AnimationSet(true).apply {
            addAnimation(fadeIn)
            addAnimation(scaleUp)
            addAnimation(rotate)
        }

        logo.startAnimation(animSet)
        titulo.startAnimation(fadeIn)

        // 🔁 Pasar a MainActivity después de 2.5 segundos
        logo.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2500)
    }
}
