package com.example.fdapp.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.fdapp.model.Category

class CategoryDiffCalldack: DiffUtil.ItemCallback<Category>( ) {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }
}
