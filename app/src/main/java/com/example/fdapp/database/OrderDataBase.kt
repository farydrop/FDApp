package com.example.fdapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlin.coroutines.coroutineContext

@Database(entities = arrayOf(Order::class), version = 1, exportSchema = false)
public abstract class OrderDataBase : RoomDatabase() {

    abstract fun orderDao(): OrderDao

    private class OrderDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.orderDao())
                }
            }
        }

        suspend fun populateDatabase(orderDao: OrderDao) {
            // Delete all content here.
            /*orderDao.deleteAll()

            // Add sample words.
            var word = Order("ffff", "ffff", 2, 2)
            orderDao.insert(word)
            word = Order("ffff", "ffff", 2, 2)
            orderDao.insert(word)*/

            // TODO: Add your own words!
        }
    }

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: OrderDataBase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): OrderDataBase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    OrderDataBase::class.java,
                    "order_database"
                )
                    .addCallback(OrderDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}
