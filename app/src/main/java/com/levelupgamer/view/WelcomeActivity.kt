package com.levelupgamer.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.levelupgamer.view.LoginActivity
import com.levelupgamer.R

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        // nombre del usuario desde LoginActivity o RegisterActivity
        val nombreUsuario = intent.getStringExtra("nombreUsuario") ?: "Jugador"

        // Referencias a los elementos visuales del layout
        val txtBienvenida = findViewById<TextView>(R.id.txtBienvenida)
        val btnIrProductos = findViewById<Button>(R.id.btnIrProductos)
        val btnCerrarSesion = findViewById<Button>(R.id.btnCerrarSesion)

        // Mostramos el mensaje personalizado
        txtBienvenida.text = "¡Bienvenido, $nombreUsuario! 🎮"

        // Botón que lleva al catálogo principal (MainActivity)
        btnIrProductos.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Botón que cierra sesión y regresa al LoginActivity
        btnCerrarSesion.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
