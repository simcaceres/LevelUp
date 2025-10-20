package com.levelupgamer.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.levelupgamer.view.LoginActivity
import com.levelupgamer.R

class RegisterActivity : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Inicializamos SharedPreferences
        prefs = getSharedPreferences("Usuarios", MODE_PRIVATE)

        // Referencias del layout
        val nombre = findViewById<EditText>(R.id.txtNombre)
        val email = findViewById<EditText>(R.id.txtEmail)
        val password = findViewById<EditText>(R.id.txtPassword)
        val confirmar = findViewById<EditText>(R.id.txtConfirmar)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        val btnVolver = findViewById<Button>(R.id.btnVolverLogin)

        // Botón registrar usuario
        btnRegistrar.setOnClickListener {
            val nombreStr = nombre.text.toString().trim()
            val emailStr = email.text.toString().trim()
            val passStr = password.text.toString().trim()
            val confStr = confirmar.text.toString().trim()

            // Validaciones básicas
            if (nombreStr.isEmpty() || emailStr.isEmpty() || passStr.isEmpty() || confStr.isEmpty()) {
                Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()) {
                Toast.makeText(this, "Correo inválido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (passStr.length < 6) {
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (passStr != confStr) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Guardamos datos en SharedPreferences
            val editor = prefs.edit()
            editor.putString("nombre", nombreStr)
            editor.putString("email", emailStr)
            editor.putString("password", passStr)
            editor.apply()

            Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()

            // Ir a la pantalla de login
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Botón volver
        btnVolver.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
