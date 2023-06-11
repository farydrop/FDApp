package com.example.fdapp.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fdapp.databinding.DishesItemBinding
import com.example.fdapp.databinding.OrderItemBinding
import com.example.fdapp.model.Dishes
import com.example.fdapp.model.Order
import com.squareup.picasso.Picasso

class OrderitemAdapter(private val orderList: ArrayList<Order>): RecyclerView.Adapter<OrderitemAdapter.OrderItemViewHolder>() {

    //private var orderList = emptyList<Order>()

    class OrderItemViewHolder(private val binding: OrderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        val orderImg = binding.ivDishesImageOrder
        val orderName = binding.tvOrderTitle
        val price = binding.tvPriceOrder
        val weight = binding.tvWeightOrder

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderItemViewHolder {
        val view = OrderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val viewHolder = OrderItemViewHolder(view)
        //viewHolder.itemView.setOnClickListener(this)
        return viewHolder
    }

    override fun getItemCount(): Int = orderList.size

    override fun onBindViewHolder(holder: OrderItemViewHolder, position: Int) {
        val orders = orderList[position]
        holder.orderName.text = orders.name
        holder.price.text = orders.price.toString()
        holder.weight.text = orders.weight.toString()
        Picasso.with(holder.orderImg.context)
            .load(orders.image_url)
            .into(holder.orderImg)
        //holder.itemView.tag = dishes
    }


}