package com.example.fdapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.fdapp.R
import com.example.fdapp.databinding.ActivityMainBinding
import com.example.fdapp.model.Category
import com.example.fdapp.presentation.adapters.CategoryAdapter
import com.example.fdapp.presentation.adapters.OnClickListener
import com.example.fdapp.viewmodel.MainViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var categoryAdapter: CategoryAdapter? = null
    private var recyclerView: RecyclerView? = null
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNavigation()

        categoryAdapter = CategoryAdapter(object : OnClickListener {
            override fun onClick(category: Category) {

                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                intent.putExtra(NEXT_SCREEN, category.name)
                startActivity(intent)


            }
        })
        binding.rvCategory.adapter = categoryAdapter
        viewModel.getCategoryList()
        viewModel.categoryList.observe(this) { list ->
            list.body()?.let {
                categoryAdapter?.setList(it.category)
            }
        }


        viewModel.showCategoryItemActivity.observe(this) {
            /*startActivity(
                Intent(
                    this@MainActivity,
                    SecondActivity::class.java
                )
            )*/
            /*val intent = Intent(this@MainActivity, SecondActivity::class.java)
            // Passing the data to the
            // EmployeeDetails Activity
            intent.putExtra(NEXT_SCREEN, model)
            startActivity(intent)*/
        }
    }

    private fun setupBottomNavigation() {
        with(binding.bnvMain) {
            selectedItemId = R.id.nav_home
            itemIconTintList = null
            setOnNavigationItemSelectedListener(
                BottomNavigationView.OnNavigationItemSelectedListener { item ->
                    when (item.itemId) {
                        R.id.nav_home -> {
                            return@OnNavigationItemSelectedListener true
                        }
                        R.id.nav_search -> {
                            return@OnNavigationItemSelectedListener true
                        }
                        R.id.nav_bag -> {
                            val intent = Intent(this@MainActivity, ThirdActivity::class.java)
                            startActivity(intent)
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

    companion object{
        const val NEXT_SCREEN="details_screen"
    }

}