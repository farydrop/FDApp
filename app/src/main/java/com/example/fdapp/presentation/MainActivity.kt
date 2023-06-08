package com.example.fdapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.fdapp.databinding.ActivityMainBinding
import com.example.fdapp.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var recyclerView: RecyclerView
    val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.rvCategory
        categoryAdapter = CategoryAdapter()
        recyclerView.adapter = categoryAdapter
        viewModel.getCategoryList()
        viewModel.categoryList.observe(this) { list ->
            list.body()?.let {
                categoryAdapter.setList(it.category)
            }
        }
    }
}