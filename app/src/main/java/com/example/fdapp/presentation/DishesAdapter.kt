package com.example.fdapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fdapp.R
import com.example.fdapp.databinding.DishesItemBinding
import com.example.fdapp.model.Category
import com.example.fdapp.model.Dishes
import com.squareup.picasso.Picasso

interface OnDishesClickListener {
    fun onClick(dishes: Dishes)
}

class DishesAdapter(private val onDishesClicked: OnDishesClickListener): RecyclerView.Adapter<DishesAdapter.DishesViewHolder>(),View.OnClickListener {

    private var dishesList = emptyList<Dishes>()

    class DishesViewHolder(private val binding: DishesItemBinding): RecyclerView.ViewHolder(binding.root) {

        val dishesImg = binding.ivDishesImage
        val dishesName = binding.tvDishesTitle

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DishesViewHolder {
        val view = DishesItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        val viewHolder = DishesViewHolder(view)
        viewHolder.itemView.setOnClickListener(this)
        return viewHolder
    }

    override fun getItemCount(): Int = dishesList.size

    override fun onBindViewHolder(holder: DishesViewHolder, position: Int) {
        val dishes = dishesList[position]
        holder.dishesName.text = dishes.name
        Picasso.with(holder.dishesImg.context)
            .load(dishes.image_url)
            .into(holder.dishesImg)
        holder.itemView.tag = dishes
    }

    override fun onClick(view: View?) {
        val dishes = view?.tag as Dishes
        onDishesClicked.onClick(dishes)
    }

    fun setList(dishes: List<Dishes>) {
        dishesList = dishes
        notifyDataSetChanged()
    }
}