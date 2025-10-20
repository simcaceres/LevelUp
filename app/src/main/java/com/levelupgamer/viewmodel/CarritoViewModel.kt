package com.levelupgamer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.levelupgamer.model.Producto

class CarritoViewModel : ViewModel() {

    private val _carrito = MutableLiveData<MutableList<Producto>>(mutableListOf())
    val carrito: LiveData<MutableList<Producto>> = _carrito

    fun agregarAlCarrito(producto: Producto) {
        val listaActual = _carrito.value ?: mutableListOf()
        val productoExistente = listaActual.find { it.id == producto.id }

        if (productoExistente != null) {
            productoExistente.cantidad++
        } else {
            producto.cantidad = 1
            listaActual.add(producto)
        }
        _carrito.value = listaActual
    }

    fun eliminarDelCarrito(producto: Producto) {
        val listaActual = _carrito.value ?: mutableListOf()
        listaActual.remove(producto)
        _carrito.value = listaActual
    }

    fun obtenerTotal(): Double {
        return _carrito.value?.sumOf { it.precio * it.cantidad } ?: 0.0
    }
}
