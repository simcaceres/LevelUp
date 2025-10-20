package com.levelupgamer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.levelupgamer.model.Producto

class ProductoViewModel : ViewModel() {

    // Lista observable de productos
    private val _listaProductos = MutableLiveData<List<Producto>>()
    val listaProductos: LiveData<List<Producto>> get() = _listaProductos

    init {
        // Cargamos algunos productos
        _listaProductos.value = listOf(
            Producto(1, "Catan", "Juego de estrategia clásico para 3-4 jugadores.", 29990.0, "catan"),
            Producto(2, "Carcassonne", "Construye fortalezas medievales en este juego de fichas.", 24990.0, "carcassonne"),
            Producto(3, "PlayStation 5", "Consola de nueva generación de Sony.", 549990.0, "ps5"),
            Producto(4, "Control Xbox Series X", "Control ergonómico compatible con Xbox y PC.", 59990.0, "xbox_controller"),
            Producto(5, "Audífonos HyperX Cloud II", "Sonido envolvente 7.1 con micrófono desmontable.", 79990.0, "hyperx"),
            Producto(6, "PC Gamer ASUS ROG Strix", "Potente equipo para gaming extremo.", 1299990.0, "rog_strix")
        )
    }
}
