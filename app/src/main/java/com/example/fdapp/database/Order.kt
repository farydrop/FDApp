package com.example.fdapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_table")
class Order(
    @PrimaryKey
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image_url")val image_url: String,
    @ColumnInfo(name = "price") var price: Int,
    @ColumnInfo(name = "weight")val weight: Int,
    @ColumnInfo(name = "count") var count: Int,
)
