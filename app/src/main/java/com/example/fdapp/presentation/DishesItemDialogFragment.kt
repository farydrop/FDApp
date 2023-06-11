package com.example.fdapp.presentation

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.fdapp.R
import com.example.fdapp.databinding.DishesItemDialogFragmentBinding
import com.example.fdapp.viewmodel.DishesItemDialogFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.net.URL


class DishesItemDialogFragment(): DialogFragment() {

    private lateinit var binding: DishesItemDialogFragmentBinding
    private val viewModel: DishesItemDialogFragmentViewModel by viewModel()

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

        binding.tvDialogTitle.text = name
        binding.tvPriceDialog.text = price
        binding.tvWeightDialog.text =weight
        binding.tvDescriptionDialog.text = description
        val bitmap = BitmapFactory.decodeStream(URL(image).openConnection().getInputStream())
        binding.ivDishesImageInDialog.setImageBitmap(bitmap)
        val intent = Intent(requireContext(), ThirdActivity::class.java)

        binding.btnAddOrder.setOnClickListener{
            intent.putExtra("NAME", name)
            intent.putExtra("IMAGE", image)
            intent.putExtra("PRICE", price)
            intent.putExtra("WEIGHT", weight)
        }

        binding.ivFavButton.setOnClickListener {
            startActivity(intent)
            dismiss()
        }


        return binding.root
    }

}