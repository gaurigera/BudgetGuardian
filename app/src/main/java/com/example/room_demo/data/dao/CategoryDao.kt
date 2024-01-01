package com.example.room_demo.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.room_demo.data.data_classes.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCategory(category: Category)

    @Query("select * from categories")
    fun readAllData(): Flow<List<Category>>

    @Query("select sum(spent) from category_items where id = :id")
    fun sumOfSpent(id: Int): Double

    @Query("update categories set amountSpend = :spent where id = :id")
    suspend fun updateSpent(id: Int, spent: Long)
}
