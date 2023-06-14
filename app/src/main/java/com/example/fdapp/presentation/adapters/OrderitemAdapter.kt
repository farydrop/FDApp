package com.example.fdapp.presentation.adapters

import android.content.Intent
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fdapp.databinding.OrderItemBinding
import com.example.fdapp.database.Order
import com.example.fdapp.model.Dishes
import com.example.fdapp.presentation.MainActivity
import com.example.fdapp.presentation.SecondActivity
import com.example.fdapp.presentation.ThirdActivity
import com.squareup.picasso.Picasso

class OrderitemAdapter :
    ListAdapter<Order, OrderitemAdapter.OrderItemViewHolder>(OrderItemDiffCallback()){

    //private var orderList = emptyList<Order>()

    class OrderItemViewHolder(private val binding: OrderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val orderImg = binding.ivDishesImageOrder
        val orderName = binding.tvOrderTitle
        val price = binding.tvPriceOrder
        val weight = binding.tvWeightOrder

        val minus = binding.ivMinusButton
        val plus = binding.ivPlusButton

        val count = binding.tvCount

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderItemViewHolder {
        val view = OrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = OrderItemViewHolder(view)
        //viewHolder.itemView.setOnClickListener(this)
        return viewHolder
    }

    override fun onBindViewHolder(holder: OrderItemViewHolder, position: Int) {

        val current = getItem(position)
        holder.orderName.text = current.name
        holder.price.text = current.price.toString()
        holder.weight.text = current.weight.toString()
        holder.count.text = current.count.toString()
        Picasso.with(holder.orderImg.context)
            .load(current.image_url)
            .into(holder.orderImg)

        holder.itemView.tag = current

        var x = 1
        //var price = current.price
        holder.minus.setOnClickListener {
            //price -= price / x
            x -= 1
            holder.count.text = x.toString()
            //holder.price.text = price.toString()
        }
        holder.plus.setOnClickListener {
            //price += price / x
            x += 1
            holder.count.text = x.toString()
            //holder.price.text = price.toString()

        }
    }
}

