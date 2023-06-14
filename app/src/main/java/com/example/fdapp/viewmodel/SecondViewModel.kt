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
import com.example.fdapp.model.Tag
import kotlinx.coroutines.launch
import retrofit2.Response

class SecondViewModel : ViewModel() {

    var repo = GetDishesListRepository()
    val dishesList: MutableLiveData<Response<DishesList>> = MutableLiveData()
    val tagState = MutableLiveData<ArrayList<Tag>>()
    private val tagData = ArrayList<Tag>()

    init {
        tagData.add(Tag("Все меню", true))
        tagData.add(Tag("Салаты", false))
        tagData.add(Tag("С рисом", false))
        tagData.add(Tag("С рыбой", false))
        tagState.value = tagData
    }

    fun getDishesList() {
        viewModelScope.launch {
            dishesList.value = repo.getDishesApi()
        }
    }


}