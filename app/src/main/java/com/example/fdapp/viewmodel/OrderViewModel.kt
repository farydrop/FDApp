package com.example.fdapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.fdapp.database.Order
import com.example.fdapp.database.OrderRepository
import kotlinx.coroutines.launch

class OrderViewModel(private val repository: OrderRepository): ViewModel() {

    val allOrders: LiveData<List<Order>> = repository.allOrders.asLiveData()

    fun insert(order: Order) = viewModelScope.launch {
        repository.insert(order)
    }


}

class OrderViewModelFactory(private val repository: OrderRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OrderViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OrderViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}