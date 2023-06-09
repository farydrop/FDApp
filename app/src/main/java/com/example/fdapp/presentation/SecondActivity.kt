package com.example.fdapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fdapp.databinding.ActivitySecondBinding
import com.example.fdapp.model.Category
import com.example.fdapp.model.Dishes
import com.example.fdapp.viewmodel.MainViewModel
import com.example.fdapp.viewmodel.SecondViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var dishesAdapter: DishesAdapter? = null
    val viewModel: SecondViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name: String? = intent.getStringExtra(MainActivity.NEXT_SCREEN)
        binding.tvTitle.text = name

        binding.ivArrowBack.setOnClickListener {
            onBackPressed()
        }


        val layoutManager = GridLayoutManager(this, 3)
        binding.rvDishes.layoutManager = layoutManager
        dishesAdapter = DishesAdapter(object : OnDishesClickListener {
            override fun onClick(dishes: Dishes) {
                val toast = Toast.makeText(this@SecondActivity, dishes.name,Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.BOTTOM,0,160)
                toast.show()
            }
        })

        binding.rvDishes.adapter = dishesAdapter
        viewModel.getDishesList()
        viewModel.dishesList.observe(this) { list ->
            list.body()?.let {
                dishesAdapter?.setList(it.dishes)
            }
        }

    }
}