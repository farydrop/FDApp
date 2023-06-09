package com.example.fdapp.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.fdapp.model.Category
import com.example.fdapp.model.Tag

class TagDiffCalldack: DiffUtil.ItemCallback<Tag>( ) {
    override fun areItemsTheSame(oldItem: Tag, newItem: Tag): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Tag, newItem: Tag): Boolean {
        return oldItem == newItem
    }
}
