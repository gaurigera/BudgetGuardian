package com.example.room_demo.data.data_classes

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(
    tableName = "category_items",
    foreignKeys = [ForeignKey(
        entity = Category::class,
        parentColumns = ["id"],
        childColumns = ["categoryId"],
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
    )]
)
data class CategoryItems(
    @PrimaryKey
    var id: Int,
    var categoryId : Int,
    var spent : Double,
    var description : String,
    var date : Date
)
