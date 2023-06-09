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
import com.example.fdapp.databinding.DishesItemBinding
import com.example.fdapp.databinding.TagItemEnableBinding
import com.example.fdapp.model.Category
import com.example.fdapp.model.Tag
import com.squareup.picasso.Picasso

interface OnTagClickListener {
    fun onTagClick(tag: Tag)
}
class TagAdapter(private val tags: ArrayList<Tag>, private val onTagClickListener: OnTagClickListener) : RecyclerView.Adapter<TagAdapter.TagViewHolder>(), View.OnClickListener {


    class TagViewHolder(private val binding: TagItemEnableBinding): RecyclerView.ViewHolder(binding.root) {
        val tagName = binding.tvTagName
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagViewHolder {
        val view = TagItemEnableBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val viewHolder = TagViewHolder(view)
        viewHolder.itemView.setOnClickListener(this)
        return viewHolder
    }

    override fun onBindViewHolder(holder: TagViewHolder, position: Int) {
        //val frequency = frequencyList[position]
        val tagItem = tags[position]
        holder.tagName.text = tagItem.name
        holder.itemView.tag = tagItem


    }

    override fun getItemCount(): Int = tags.size
    override fun onClick(v: View?) {
        val tagItem = v?.tag as Tag
        onTagClickListener.onTagClick(tagItem)
    }
}