package com.levelupgamer.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.levelupgamer.model.Producto

@Dao
interface ProductoDao {

    @Query("SELECT * FROM productos")
    fun getAllProductos(): LiveData<List<Producto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(productos: List<Producto>)

    @Query("DELETE FROM productos")
    suspend fun deleteAll()
}
