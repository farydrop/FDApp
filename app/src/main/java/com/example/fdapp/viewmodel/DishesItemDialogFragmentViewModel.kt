package com.example.fdapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fdapp.data.repository.GetCategoryItemRepository
import com.example.fdapp.data.repository.GetCategoryListRepository
import com.example.fdapp.model.Category
import com.example.fdapp.model.CategoryList
import kotlinx.coroutines.launch
import retrofit2.Response

class DishesItemDialogFragmentViewModel: ViewModel() {

    var repo = GetCategoryItemRepository()
    val myCategoryItemFragment: MutableLiveData<Response<Category>> = MutableLiveData()

    fun getCategoryItemFragment(){
        viewModelScope.launch {
            myCategoryItemFragment.value = repo.getCategoryItemApi()
        }
    }

}
