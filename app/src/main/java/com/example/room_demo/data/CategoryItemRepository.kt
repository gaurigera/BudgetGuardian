package com.example.room_demo.data

import androidx.annotation.WorkerThread
import com.example.room_demo.data.dao.CategoryItemDao

class CategoryItemRepository(private val categoryItemDao: CategoryItemDao) {
    @WorkerThread
    suspend fun getItemByCategory(categoryId : Int) {
        categoryItemDao.getItemsByCategory(categoryId)
    }
}