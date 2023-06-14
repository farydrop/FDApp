package com.example.fdapp.presentation

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.fdapp.App
import com.example.fdapp.R
import com.example.fdapp.database.Order
import com.example.fdapp.databinding.ActivityThirdBinding
import com.example.fdapp.presentation.adapters.OrderitemAdapter
import com.example.fdapp.viewmodel.OrderViewModel
import com.example.fdapp.viewmodel.OrderViewModelFactory
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.text.DecimalFormat


class ThirdActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThirdBinding
    private var orderitemAdapter: OrderitemAdapter? = null
    private val viewModel: OrderViewModel by viewModels {
        OrderViewModelFactory((application as App).repository)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNavigation()


        val b = intent.extras
        //val data1 = b!!.getString("DATA")

        val name = b?.getString("NAME")
        val image: String? = b?.getString("IMAGE")
        val price: String? = b?.getString("PRICE")
        val weight: String? = b?.getString("WEIGHT")
        val count: String? = b?.getString("COUNT")

        if(intent.extras == null) {
            //
        } else {
            if (name!=null && image!=null && price!=null && weight!= null && count!=null){
                val list = Order(name,image, price.toInt(),weight.toInt(), count.toInt())
                viewModel.insert(list)

                val arrayList = ArrayList<Order>()
                arrayList.add(list)


            }
        }



        orderitemAdapter = OrderitemAdapter()
        binding.rvOrderItem.adapter = orderitemAdapter
        viewModel.allOrders.observe(this, Observer { orders ->
            orders?.let {
                orderitemAdapter!!.submitList(it)
                calculateTotalAmount(it)
                for (order in it){
                    if (order.count>1) {
                        order.price *= 2
                    }
                }
                orderitemAdapter?.notifyDataSetChanged()
            }
        })
    }

    private fun setupBottomNavigation() {
        with(binding.bnvThird) {
            selectedItemId = R.id.nav_bag
            itemIconTintList = null
            setOnNavigationItemSelectedListener(
                BottomNavigationView.OnNavigationItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.nav_home -> {
                            val intent = Intent(this@ThirdActivity, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                            return@OnNavigationItemSelectedListener true
                        }
                        R.id.nav_search -> {
                            return@OnNavigationItemSelectedListener true
                        }
                        R.id.nav_bag -> {
                            return@OnNavigationItemSelectedListener true
                        }
                        R.id.nav_profile -> {
                            return@OnNavigationItemSelectedListener true
                        }
                    }
                    false
                }
            )
        }
    }

    private fun calculateTotalAmount(order: List<Order>) {
        var subTotalAmount = 0f
        val df = DecimalFormat("###,###.#")
        for (m in order) {
            subTotalAmount += m.price
            binding.btnToPay.text = "Оплатить " + df.format(subTotalAmount) + " ₽"
        }
    }

}