package com.example.levelupgamer.model
import com.levelupgamer.model.Producto
import com.levelupgamer.model.Usuario

data class Reseña(
    val id: Int,
    val usuario: Usuario,
    val producto: Producto,
    val comentario: String,
    val calificacion: Int // de 1 a 5 estrellas
)
