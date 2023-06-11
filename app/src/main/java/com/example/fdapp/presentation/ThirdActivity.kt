package com.example.fdapp.presentation

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fdapp.databinding.ActivityThirdBinding
import com.example.fdapp.model.Order
import com.example.fdapp.presentation.adapters.OrderitemAdapter

class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding
    private var orderitemAdapter: OrderitemAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name: String = intent.getStringExtra("NAME")?: "null"
        val image: String = intent.getStringExtra("IMAGE")?: "null"
        val price: String = intent.getStringExtra("PRICE")?: "null"
        val weight: String = intent.getStringExtra("WEIGHT")?: "null"

        val orderList = mutableListOf<Order>()
        orderList.add(Order(image,name, price.toInt(),weight.toInt()))

        orderitemAdapter = OrderitemAdapter(orderList as ArrayList<Order>)
        binding.rvOrderItem.adapter = orderitemAdapter
        orderitemAdapter?.notifyDataSetChanged()

        binding.btnToPay.text = "Оплатить $price"
    }

}