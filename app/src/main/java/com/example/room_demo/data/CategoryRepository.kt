package com.example.room_demo.data

import com.example.room_demo.data.dao.CategoryDao
import kotlinx.coroutines.flow.Flow

class CategoryRepository(
    categoryDao: CategoryDao
) {
    val categoryNames : Flow<List<String>> =categoryDao.readCategoryNames()
}