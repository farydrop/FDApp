package com.example.fdapp.database

import androidx.annotation.MainThread
import kotlinx.coroutines.flow.Flow

class OrderRepository(private val orderDao: OrderDao) {

    val allOrders: Flow<List<Order>> = orderDao.getAlphabetized()

    @Suppress("RedundantSuspendModifier")
    @MainThread
    suspend fun insert(order: Order) {
        orderDao.insert(order)
    }
}