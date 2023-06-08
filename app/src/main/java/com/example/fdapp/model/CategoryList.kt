package com.example.fdapp.model

import com.google.gson.annotations.SerializedName

data class CategoryList(
    @SerializedName(value = "сategories")
    val category: List<Category>
)
