package com.example.room_demo.data

import com.example.room_demo.data.dao.CategoryDao
import com.example.room_demo.data.data_classes.Category
import kotlinx.coroutines.flow.Flow

class CategoryRepository(
    private val categoryDao: CategoryDao
) {
    val categoryNames: Flow<List<String>> = categoryDao.readCategoryNames()

    suspend fun insertCategory(category: Category) {
        try {
            categoryDao.addCategory(category)
        } catch (ex: Exception) {
            println(ex.message)
        }
    }
}