package com.levelupgamer.data

import androidx.lifecycle.LiveData
import com.levelupgamer.model.Producto

class ProductoRepository(private val productoDao: ProductoDao) {

    val allProductos: LiveData<List<Producto>> = productoDao.getAllProductos()

}
