package com.example.levelupgamer.model
import com.levelupgamer.model.Producto
import com.levelupgamer.model.Usuario

data class Favorito(
    val id: Int,
    val usuario: Usuario,
    val producto: Producto
)
