package com.example.room_demo.data

import com.example.room_demo.data.dao.CategoryDao
import com.example.room_demo.data.data_classes.Category
import kotlinx.coroutines.flow.Flow

class CategoryRepository(
    categoryDao: CategoryDao
) {
    val allCategories: Flow<List<Category>> = categoryDao.readAllData()
}