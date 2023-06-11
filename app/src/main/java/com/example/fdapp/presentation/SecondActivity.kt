package com.example.fdapp.presentation

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
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

                var dialog = DishesItemDialogFragment()
                //dialog.show(supportFragmentManager, "dishesDialog")

                //val dialogFragment: Dialog = Dialog()
                val bundle = Bundle()
                bundle.putString("NAME", dishes.name)
                bundle.putString("IMAGE", dishes.image_url)
                bundle.putString("PRICE", dishes.price.toString())
                bundle.putString("WEIGHT", dishes.weight.toString())
                bundle.putString("DESCRIPTION", dishes.description)
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