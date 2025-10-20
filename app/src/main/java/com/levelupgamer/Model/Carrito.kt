package com.levelupgamer.model
import com.levelupgamer.model.Producto
import com.levelupgamer.model.Usuario
data class ItemCarrito(
    val producto: Producto,
    var cantidad: Int
)
data class Carrito(
    val usuario: Usuario,
    val items: MutableList<ItemCarrito> = mutableListOf()
) {
    fun calcularTotal(): Double {
        return items.sumOf { it.producto.precio * it.cantidad }
    }
    fun agregarProducto(producto: Producto) {
        val existente = items.find { it.producto.id == producto.id }
        if (existente != null) {
            existente.cantidad++
        } else {
            items.add(ItemCarrito(producto, 1))
        }
    }
    fun eliminarProducto(producto: Producto) {
        val existente = items.find { it.producto.id == producto.id }
        if (existente != null) {
            existente.cantidad--
            if (existente.cantidad <= 0) {
                items.remove(existente)
            }
        }
    }
    fun vaciarCarrito() {
        items.clear()
    }
}
