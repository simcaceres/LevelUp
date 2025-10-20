package com.example.levelupgamer.model
import com.levelupgamer.model.Producto
import com.levelupgamer.model.Usuario

data class Pedido(
    val idPedido: Int,
    val usuario: Usuario,
    val productos: List<Producto>,
    val total: Double,
    val fecha: String
)
