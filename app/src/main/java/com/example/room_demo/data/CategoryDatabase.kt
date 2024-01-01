package com.example.room_demo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.room_demo.data.dao.CategoryDao
import com.example.room_demo.data.dao.CategoryItemDao
import com.example.room_demo.data.data_classes.Category
import com.example.room_demo.data.data_classes.CategoryItems

@Database(entities = [Category::class, CategoryItems::class], version = 1, exportSchema = false)
abstract class CategoryDatabase : RoomDatabase() {

    abstract fun categoryDao(): CategoryDao
    abstract fun categoryItemDao() : CategoryItemDao //modify the companion object

    companion object {
        @Volatile
        private var INSTANCE: CategoryDatabase? = null

        fun getDatabase(context: Context): CategoryDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CategoryDatabase::class.java,
                    "categories"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}