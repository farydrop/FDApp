package com.example.fdapp.presentation

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.fdapp.R
import com.example.fdapp.databinding.CategoryItemBinding
import com.example.fdapp.model.Category
import com.squareup.picasso.Picasso

class CategoryAdapter: RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    var categoryList = emptyList<Category>()

    inner class CategoryViewHolder(private val itemBinding: CategoryItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(category: Category) {
            itemBinding.tvCategoryTitle.text = category.name
        }

        val img = itemBinding.ivCategoryImage
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            CategoryItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = categoryList.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoryList[position]

        Picasso.with(holder.img.context)
            .load(categoryList[position].image_url)
            .into(holder.img)

        holder.bind(category)
    }

    fun setList(category: List<Category>) {
        categoryList = category
        notifyDataSetChanged()
    }
}