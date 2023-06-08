package com.example.fdapp.data.repository

import com.example.fdapp.data.api.RetrofitInstance
import com.example.fdapp.model.Category
import com.example.fdapp.model.CategoryList
import retrofit2.Response

class GetCategoryListRepository {

    suspend fun getCategoryApi(): Response<CategoryList> {
        return RetrofitInstance.api.getCategory()
    }
}