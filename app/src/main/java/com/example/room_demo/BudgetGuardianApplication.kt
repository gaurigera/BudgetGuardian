package com.example.room_demo

import android.app.Application
import com.example.room_demo.data.CategoryDatabase
import com.example.room_demo.data.CategoryItemRepository
import com.example.room_demo.data.CategoryRepository

class BudgetGuardianApplication : Application() {
    val categoryDatabase by lazy { CategoryDatabase.getDatabase(this) }
    val categoryRepository by lazy { CategoryRepository(categoryDatabase.categoryDao()) }
    val categoryItemRepository by lazy { CategoryItemRepository(categoryDatabase.categoryItemDao()) }
    fun getCategoryRepo(): CategoryRepository {
        return categoryRepository
    }
    fun getCategoryItemRepo(): CategoryItemRepository {
        return categoryItemRepository
    }
}