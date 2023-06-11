package com.example.fdapp.data.api

import com.example.fdapp.model.Category
import com.example.fdapp.model.CategoryList
import com.example.fdapp.model.DishesList
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("058729bd-1402-4578-88de-265481fd7d54")
    suspend fun getCategoryList(): Response<CategoryList>

    @GET("c7a508f2-a904-498a-8539-09d96785446e")
    suspend fun getDishes(): Response<DishesList>

    @GET("058729bd-1402-4578-88de-265481fd7d54")
    suspend fun getCategoryItem(): Response<Category>

}