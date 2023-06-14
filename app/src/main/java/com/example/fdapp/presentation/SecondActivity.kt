package com.example.fdapp.presentation

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.fdapp.R
import com.example.fdapp.databinding.ActivitySecondBinding
import com.example.fdapp.model.Dishes
import com.example.fdapp.model.Tag
import com.example.fdapp.presentation.adapters.DishesAdapter
import com.example.fdapp.presentation.adapters.OnDishesClickListener
import com.example.fdapp.presentation.adapters.OnTagClickListener
import com.example.fdapp.presentation.adapters.TagAdapter
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
        setupBottomNavigation()

        val name: String? = intent.getStringExtra(MainActivity.NEXT_SCREEN)
        binding.tvTitle.text = name

        binding.ivArrowBack.setOnClickListener {
            onBackPressed()
        }


        val count = 2
        val layoutManager = GridLayoutManager(this, 3)
        binding.rvDishes.layoutManager = layoutManager
        dishesAdapter = DishesAdapter(object : OnDishesClickListener {
            override fun onClick(dishes: Dishes) {
                /*val toast = Toast.makeText(this@SecondActivity, dishes.name, Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.BOTTOM, 0, 160)
                toast.show()*/

                var dialog = DishesItemDialogFragment()
                val bundle = Bundle()
                bundle.putString("NAME", dishes.name)
                bundle.putString("IMAGE", dishes.image_url)
                bundle.putString("PRICE", dishes.price.toString())
                bundle.putString("WEIGHT", dishes.weight.toString())
                bundle.putString("DESCRIPTION", dishes.description)
                //bundle.putString("COUNT", count.toString())
                dialog.arguments = bundle
                dialog.show(this@SecondActivity.supportFragmentManager, "dishesDialog")
            }
        })
        binding.rvDishes.adapter = dishesAdapter
        viewModel.getDishesList()
        viewModel.dishesList.observe(this) { list ->
            list.body()?.let {
                dishesAdapter?.setList(it.dishes)
            }
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

    }

    private fun setupBottomNavigation() {
        with(binding.bnvSecond) {
            selectedItemId = R.id.nav_home
            itemIconTintList = null
            setOnNavigationItemSelectedListener(
                com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.nav_home -> {
                            return@OnNavigationItemSelectedListener true
                        }
                        R.id.nav_search -> {
                            return@OnNavigationItemSelectedListener true
                        }
                        R.id.nav_bag -> {

                            val newIntent = Intent(this@SecondActivity, ThirdActivity::class.java)
                            newIntent.flags = Intent.FLAG_ACTIVITY_FORWARD_RESULT
                            val bundle = intent.extras
                            if (bundle != null) {
                                newIntent.putExtras(bundle)
                            }
                            startActivity(newIntent)
                            finish()



                            /*val nameDish: String? = intent.getStringExtra("NAME")
                            val image: String? = intent.getStringExtra("IMAGE")
                            val price: String? = intent.getStringExtra("PRICE")
                            val weight: String? = intent.getStringExtra("WEIGHT")*/

                            /*val intentThirdAct = Intent(this@SecondActivity, ThirdActivity::class.java)
                            intentThirdAct.putExtra("NAME", nameDish)
                            intentThirdAct.putExtra("IMAGE", image)
                            intentThirdAct.putExtra("PRICE", price)
                            intentThirdAct.putExtra("WEIGHT", weight)*/
                            //startActivity(intentThirdAct)


                            finish()
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
}