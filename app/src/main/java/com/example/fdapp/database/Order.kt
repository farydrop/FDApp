package com.example.fdapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "order_table")
class Order(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "image_url")val image_url: String,
    @ColumnInfo(name = "price")val price: Int,
    @ColumnInfo(name = "weight")val weight: Int
)
