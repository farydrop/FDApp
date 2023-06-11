package com.example.fdapp.database

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

interface OrderDao {
    @Query("SELECT * FROM order_table ORDER BY name ASC")
    fun getAlphabetized(): Flow<List<Order>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(order: Order)

    @Query("DELETE FROM order_table")
    suspend fun deleteAll()
}