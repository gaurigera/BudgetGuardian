package com.example.room_demo.data

import androidx.annotation.WorkerThread
import com.example.room_demo.data.dao.CategoryItemDao
import com.example.room_demo.data.data_classes.CategoryItems

class CategoryItemRepository(private val categoryItemDao: CategoryItemDao) {
    @WorkerThread
    suspend fun getItemByCategory(categoryId : Int) {
        categoryItemDao.getItemsByCategory(categoryId)
    }

    @WorkerThread
    suspend fun insert(categoryItem: CategoryItems) {
        categoryItemDao.insertItem(categoryItem)
    }

}