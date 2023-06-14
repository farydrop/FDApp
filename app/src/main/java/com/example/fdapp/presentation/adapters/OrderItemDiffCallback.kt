package com.example.fdapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.fdapp.database.Order
import com.example.fdapp.model.Category

class OrderItemDiffCallback : DiffUtil.ItemCallback<Order>( ) {
    override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
        return oldItem.name == newItem.name
    }
}
