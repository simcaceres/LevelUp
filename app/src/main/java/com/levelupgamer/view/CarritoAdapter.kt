package com.levelupgamer.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.levelupgamer.R
import com.levelupgamer.model.Producto

class CarritoAdapter(
    private val listaCarrito: MutableList<Producto>,
    private val onEliminarClick: ((Producto) -> Unit)? = null
) : RecyclerView.Adapter<CarritoAdapter.CarritoViewHolder>() {

    class CarritoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProducto: ImageView = itemView.findViewById(R.id.imgProductoCarrito)
        val txtNombre: TextView = itemView.findViewById(R.id.txtNombreCarrito)
        val txtPrecio: TextView = itemView.findViewById(R.id.txtPrecioCarrito)
        val txtCantidad: TextView = itemView.findViewById(R.id.txtCantidadCarrito)
        val btnEliminar: Button = itemView.findViewById(R.id.btnEliminar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarritoViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto_carrito, parent, false)
        return CarritoViewHolder(view)

    }

    override fun onBindViewHolder(holder: CarritoViewHolder, position: Int) {
        val producto = listaCarrito[position]

        holder.txtNombre.text = producto.nombre
        holder.txtPrecio.text = "$${producto.precio}"
        holder.txtCantidad.text = "Cantidad: ${producto.cantidad}"

        // Cargar imagen del producto (temporal desde drawable)
        val resId = holder.itemView.context.resources.getIdentifier(
            producto.imagenUrl?.substringBefore("."), // ej. "ps5" -> R.drawable.ps5
            "drawable",
            holder.itemView.context.packageName
        )
        if (resId != 0) {
            holder.imgProducto.setImageResource(resId)
        } else {
            holder.imgProducto.setImageResource(R.drawable.ic_launcher_foreground)
        }

        // Botón eliminar producto
        holder.btnEliminar.setOnClickListener {
            onEliminarClick?.invoke(producto)
        }
    }

    override fun getItemCount(): Int = listaCarrito.size
}
