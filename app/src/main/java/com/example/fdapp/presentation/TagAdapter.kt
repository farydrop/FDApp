package com.example.fdapp.presentation

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fdapp.R
import com.example.fdapp.databinding.CategoryItemBinding
import com.example.fdapp.databinding.TagItemEnableBinding
import com.example.fdapp.model.Category
import com.example.fdapp.model.Tag
import com.squareup.picasso.Picasso

interface OnTagClickListener {
    fun onTagClick(tag: Tag)
}
class TagAdapter(private val tag: ArrayList<Tag> ) : RecyclerView.Adapter<TagAdapter.TagViewHolder>() {


    class TagViewHolder(private val binding: TagItemEnableBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(tags: Tag) {
            binding.tvTagName.text = tags.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val binding = TagItemEnableBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TagViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        //val frequency = frequencyList[position]
        val fact = tag[position]
        holder.bind(fact)
    }

    override fun getItemCount(): Int = tag.size
}