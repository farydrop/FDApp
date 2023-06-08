package com.example.fdapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fdapp.common.SingleLiveData
import com.example.fdapp.data.repository.GetCategoryListRepository
import com.example.fdapp.data.repository.GetDishesListRepository
import com.example.fdapp.model.Category
import com.example.fdapp.model.CategoryList
import com.example.fdapp.model.DishesList
import kotlinx.coroutines.launch
import retrofit2.Response

class SecondViewModel: ViewModel() {

    var repo = GetDishesListRepository()
    val dishesList: MutableLiveData<Response<DishesList>> = MutableLiveData()

    fun getDishesList() {
        viewModelScope.launch {
            dishesList.value = repo.getDishesApi()
        }
    }

}