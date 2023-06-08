package com.example.fdapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fdapp.common.SingleLiveData
import com.example.fdapp.common.SingleLiveDataEmpty
import com.example.fdapp.data.repository.GetCategoryListRepository
import com.example.fdapp.model.Category
import com.example.fdapp.model.CategoryList
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel: ViewModel() {

    var repo = GetCategoryListRepository()
    val categoryList: MutableLiveData<Response<CategoryList>> = MutableLiveData()
    val showCategoryItemActivity = SingleLiveData<Pair<Int,Category>>()

    fun getCategoryList() {
        viewModelScope.launch {
            categoryList.value = repo.getCategoryApi()
        }
    }

    fun onClick(id:Int, category: Category){
        showCategoryItemActivity.value = Pair(id,category)
    }

}