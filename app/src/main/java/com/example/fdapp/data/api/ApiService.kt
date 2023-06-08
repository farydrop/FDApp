package com.example.fdapp.data.api

import com.example.fdapp.model.Category
import com.example.fdapp.model.CategoryList
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("058729bd-1402-4578-88de-265481fd7d54")
    suspend fun getCategory(): Response<CategoryList>

}