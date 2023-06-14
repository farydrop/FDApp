package com.example.fdapp.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface OrderDao {
    @Query("SELECT * FROM order_table")
    fun getItem(): Flow<List<Order>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(order: Order)

    @Query("DELETE FROM order_table")
    suspend fun deleteAll()
}