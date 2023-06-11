package com.example.fdapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.fdapp.databinding.ActivityMainBinding
import com.example.fdapp.model.Category
import com.example.fdapp.presentation.adapters.CategoryAdapter
import com.example.fdapp.presentation.adapters.OnClickListener
import com.example.fdapp.viewmodel.MainViewModel
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

        //setupCategoryList()

        //recyclerView = binding.rvCategory
        //categoryAdapter = CategoryAdapter()
        //recyclerView.adapter = categoryAdapter
        categoryAdapter = CategoryAdapter(object : OnClickListener {
            override fun onClick(category: Category) {
                /*val toast = Toast.makeText(this@MainActivity, category.name,Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.TOP,0,160)
                toast.show()*/
                /*startActivity(
                    Intent(
                        this@MainActivity,
                        SecondActivity::class.java
                    )
                )*/

                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                // Passing the data to the
                // EmployeeDetails Activity
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

    companion object{
        const val NEXT_SCREEN="details_screen"
    }

}