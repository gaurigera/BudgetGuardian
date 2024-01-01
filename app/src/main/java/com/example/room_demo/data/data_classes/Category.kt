package com.example.room_demo.data.data_classes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val name : String,
    val amountSpend : Double,
    val amountLimit: Double
)