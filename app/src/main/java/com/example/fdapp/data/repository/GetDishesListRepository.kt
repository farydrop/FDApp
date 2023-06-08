package com.example.fdapp.data.repository

import com.example.fdapp.data.api.RetrofitInstance
import com.example.fdapp.model.CategoryList
import com.example.fdapp.model.DishesList
import retrofit2.Response

class GetDishesListRepository {

    suspend fun getDishesApi(): Response<DishesList> {
        return RetrofitInstance.api.getDishes()
    }

}