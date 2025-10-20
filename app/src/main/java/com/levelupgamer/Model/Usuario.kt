package com.levelupgamer.model

data class Usuario(
    val id: Int,
    var nombre: String,
    var email: String,
    var password: String,
    var direccion: String = "",
    var telefono: String = ""
)
