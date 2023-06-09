package com.example.fdapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract
import android.view.Gravity
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fdapp.databinding.ActivitySecondBinding
import com.example.fdapp.model.Constants
import com.example.fdapp.model.Dishes
import com.example.fdapp.model.Tag
import com.example.fdapp.viewmodel.SecondViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding
    private var dishesAdapter: DishesAdapter? = null
    private var tagAdapter: TagAdapter? = null
    private val viewModel: SecondViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name: String? = intent.getStringExtra(MainActivity.NEXT_SCREEN)
        binding.tvTitle.text = name

        binding.ivArrowBack.setOnClickListener {
            onBackPressed()
        }

        viewModel.tagState.observe(this){
            tagAdapter = TagAdapter(it, object : OnTagClickListener {

                override fun onTagClick(tag: Tag) {
                    val toast = Toast.makeText(this@SecondActivity, tag.name, Toast.LENGTH_SHORT)
                    toast.setGravity(Gravity.BOTTOM, 0, 160)
                    toast.show()
                }
            } )
            binding.rvTag.adapter = tagAdapter
        }

        /*val emplist=Constants.getTagData()
        binding.rvTag.layoutManager = LinearLayoutManager(this)
        binding.rvTag.setHasFixedSize(true)
        binding.rvTag.adapter = tagAdapter

        tagAdapter = TagAdapter(emplist, object : OnTagClickListener {

            override fun onTagClick(tag: Tag) {
                val toast = Toast.makeText(this@SecondActivity, tag.name, Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.BOTTOM, 0, 160)
                toast.show()
            }
        })*/


        val layoutManager = GridLayoutManager(this, 3)
        binding.rvDishes.layoutManager = layoutManager
        dishesAdapter = DishesAdapter(object : OnDishesClickListener {
            override fun onClick(dishes: Dishes) {
                val toast = Toast.makeText(this@SecondActivity, dishes.name, Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.BOTTOM, 0, 160)
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

    /*private fun setupTagList() {
        tagAdapter = TagAdapter().apply {
            onTagItemClickListener = { tag ->
                viewModel.onChangeEnableState(tag)
            }
        }
        binding.rvTag.adapter = tagAdapter
    }*/
}