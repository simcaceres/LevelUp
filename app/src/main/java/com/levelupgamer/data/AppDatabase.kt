package com.levelupgamer.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.levelupgamer.model.Producto
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Producto::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productoDao(): ProductoDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "levelup_database"
                )
                .addCallback(DatabaseCallback(scope))
                .build()
                INSTANCE = instance
                instance
            }
        }

        private class DatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let {
                    database ->
                    scope.launch {
                        populateDatabase(database.productoDao())
                    }
                }
            }

            suspend fun populateDatabase(productoDao: ProductoDao) {
                // Clear the database
                productoDao.deleteAll()

                // Add sample products
                val productos = listOf(
                    Producto(nombre = "Catan", descripcion = "Juego de estrategia y colonización.", precio = 29990.0, imagenUrl = "https://m.media-amazon.com/images/I/81+i0k80+FL.jpg"),
                    Producto(nombre = "Carcassonne", descripcion = "Construcción de paisajes medievales.", precio = 24990.0, imagenUrl = "https://m.media-amazon.com/images/I/81T1cTj2Y8L.jpg"),
                    Producto(nombre = "PlayStation 5", descripcion = "Consola de nueva generación.", precio = 549990.0, imagenUrl = "https://m.media-amazon.com/images/I/61t4GqA5-HL.jpg"),
                    Producto(nombre = "Audífonos HyperX Cloud II", descripcion = "Sonido envolvente y comodidad superior.", precio = 79990.0, imagenUrl = "https://m.media-amazon.com/images/I/71I-N-1L8BL.jpg"),
                    Producto(nombre = "PC Gamer ASUS ROG Strix", descripcion = "Potente equipo para jugadores exigentes.", precio = 1299990.0, imagenUrl = "https://m.media-amazon.com/images/I/71a4Z-v9+jL.jpg")
                )
                productoDao.insertAll(productos)
            }
        }
    }
}