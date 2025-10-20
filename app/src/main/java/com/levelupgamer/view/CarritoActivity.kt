package com.levelupgamer.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.levelupgamer.R
import com.levelupgamer.model.Producto

class CarritoActivity : AppCompatActivity() {

    private lateinit var recyclerCarrito: RecyclerView
    private lateinit var btnFinalizarCompra: Button
    private var listaCarrito: MutableList<Producto> = mutableListOf()
    private lateinit var adapter: CarritoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carrito)

        recyclerCarrito = findViewById(R.id.recyclerCarrito)
        btnFinalizarCompra = findViewById(R.id.btnFinalizarCompra)

        recyclerCarrito.layoutManager = LinearLayoutManager(this)

        // Cargar productos guardados localmente
        cargarCarrito()

        // Configurar adaptador
        adapter = CarritoAdapter(listaCarrito)
        recyclerCarrito.adapter = adapter

        // Botón para finalizar compra
        btnFinalizarCompra.setOnClickListener {
            if (listaCarrito.isNotEmpty()) {
                mostrarNotificacionCompra()
                Toast.makeText(this, "Compra finalizada 🎉", Toast.LENGTH_SHORT).show()
                listaCarrito.clear()
                guardarCarrito()
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "El carrito está vacío", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // --- FUNCIONES AUXILIARES ---

    // Cargar datos guardados
    private fun cargarCarrito() {
        val prefs = getSharedPreferences("carrito_prefs", MODE_PRIVATE)
        val json = prefs.getString("carrito", null)
        if (json != null) {
            val tipoLista = object : TypeToken<MutableList<Producto>>() {}.type
            listaCarrito = Gson().fromJson(json, tipoLista)
        }
    }

    // Guardar lista actual
    private fun guardarCarrito() {
        val prefs = getSharedPreferences("carrito_prefs", MODE_PRIVATE)
        val editor = prefs.edit()
        val json = Gson().toJson(listaCarrito)
        editor.putString("carrito", json)
        editor.apply()
    }

    // 🔔 Notificación local al finalizar compra
    private fun mostrarNotificacionCompra() {
        val channelId = "levelup_notif_canal"
        val notificationId = 1001

        // Crear canal (solo Android 8+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Notificaciones LevelUpGamer",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Notifica cuando se completa una compra"
            }

            val notificationManager: NotificationManager =
                getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }

        // Intent al presionar la notificación
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent, PendingIntent.FLAG_IMMUTABLE
        )

        // Construir notificación
        val builder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_gamepad) // ícono de tu app
            .setContentTitle("Compra completada 🎮")
            .setContentText("Tu pedido fue procesado correctamente.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        // Mostrarla
        with(NotificationManagerCompat.from(this)) {
            notify(notificationId, builder.build())
        }

    }
    }
}
