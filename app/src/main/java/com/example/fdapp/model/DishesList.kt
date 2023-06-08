package com.example.fdapp.model

import com.google.gson.annotations.SerializedName

data class DishesList(
    @SerializedName(value = "dishes")
    val dishes: List<Dishes>
)