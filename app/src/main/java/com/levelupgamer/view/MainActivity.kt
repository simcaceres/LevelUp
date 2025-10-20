package com.levelupgamer.view

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.activity.viewModels
import androidx.annotation.RequiresPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.levelupgamer.R
import com.levelupgamer.model.Producto
import com.levelupgamer.viewmodel.ProductoViewModel
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: ProductoAdapter
    private val productoViewModel: ProductoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar RecyclerView
        recyclerView = findViewById(R.id.recyclerProductos)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Adaptador con acción al agregar al carrito
        adapter = ProductoAdapter { producto ->
            agregarAlCarrito(producto)
            vibrarDispositivo() //
        }
        recyclerView.adapter = adapter
        // Observar productos desde ViewModel
        productoViewModel.listaProductos.observe(this) { productos ->
            adapter.actualizarLista(productos)
        }
    }
    // --- FUNCIONES AUXILIARES ---
    // Acción al agregar producto
    private fun agregarAlCarrito(producto: Producto) {
        println("Producto agregado al carrito: ${producto.nombre}")
    }
    // Vibración nativa al agregar producto
    @RequiresPermission(Manifest.permission.VIBRATE)
    private fun vibrarDispositivo() {
        val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (vibrator.hasVibrator()) {
            val efecto = VibrationEffect.createOneShot(
                150,  // duración en milisegundos
                VibrationEffect.DEFAULT_AMPLITUDE
            )
            vibrator.vibrate(efecto)
        }
    }
}
