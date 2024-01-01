package com.example.room_demo.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.room_demo.data.data_classes.CategoryItems

@Dao
interface CategoryItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertItem(categoryItem: CategoryItems)

    @Delete
    suspend fun deleteItem(categoryItem: CategoryItems)

    @Query("select * from category_items where categoryId = :categoryId")
    suspend fun getItemsByCategory(categoryId: Int): List<CategoryItems>

    @Query("select * from category_items where id = :movieId")
    suspend fun getItemDetails(movieId: Int): CategoryItems
}