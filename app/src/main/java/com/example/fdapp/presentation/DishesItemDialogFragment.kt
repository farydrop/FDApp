package com.example.fdapp.presentation

import android.app.Activity
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import com.example.fdapp.R
import com.example.fdapp.database.Order
import com.example.fdapp.databinding.DishesItemDialogFragmentBinding
import com.example.fdapp.model.Dishes
import com.example.fdapp.viewmodel.DishesItemDialogFragmentViewModel
import com.example.fdapp.viewmodel.OrderViewModel
import com.example.fdapp.viewmodel.OrderViewModelFactory
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.net.URL


class DishesItemDialogFragment(): DialogFragment() {

    private lateinit var binding: DishesItemDialogFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DishesItemDialogFragmentBinding.inflate(inflater,container,false)

        dialog?.window?.setBackgroundDrawableResource(R.drawable.dialog_fragment_background)

        binding.ivCloseButton.setOnClickListener {
            dismiss()
        }


        val bundle = arguments
        val name = bundle!!.getString("NAME", "")
        val image = bundle.getString("IMAGE", "")
        val price = bundle.getString("PRICE", "")
        val weight = bundle.getString("WEIGHT", "")
        val description = bundle.getString("DESCRIPTION", "")
        val count = bundle.getString("COUNT","")

        binding.tvDialogTitle.text = name
        binding.tvPriceDialog.text = price
        binding.tvWeightDialog.text =weight
        binding.tvDescriptionDialog.text = description
        val bitmap = BitmapFactory.decodeStream(URL(image).openConnection().getInputStream())
        binding.ivDishesImageInDialog.setImageBitmap(bitmap)

        binding.btnAddOrder.setOnClickListener{
            //val intent = Intent(requireContext(), ThirdActivity::class.java)
            /*intent.putExtra("NAME", name)
            intent.putExtra("IMAGE", image)
            intent.putExtra("PRICE", price)
            intent.putExtra("WEIGHT", weight)*/

            val intent = Intent(requireContext(), ThirdActivity::class.java)
            intent.putExtra("NAME", name)
            intent.putExtra("IMAGE", image)
            intent.putExtra("PRICE", price)
            intent.putExtra("WEIGHT", weight)
            //intent.putExtra("COUNT", count)
            startActivity(intent)
            dismiss()
        }

        binding.ivFavButton.setOnClickListener {
            dismiss()
        }


        return binding.root
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.putExtra("NAME", "")
        data?.putExtra("IMAGE", "")
        data?.putExtra("PRICE", "")
        data?.putExtra("WEIGHT", "")
    }

    companion object {
        const val NAME = "dishes_name"
        const val IMAGE = "dishes_image"
        const val PRICE = "dishes_price"
        const val WEIGHT = "dishes_weight"
        const val DESCRIPTION = "dishes_description"

        fun getDishes(dishes: Dishes): DishesItemDialogFragment {
            return DishesItemDialogFragment().apply {
                arguments = Bundle().apply {
                    getString(NAME, dishes.name)
                    getString(IMAGE,dishes.image_url)
                    getInt(PRICE,dishes.price)
                    getInt(WEIGHT, dishes.weight)
                }
            }
        }
    }

}