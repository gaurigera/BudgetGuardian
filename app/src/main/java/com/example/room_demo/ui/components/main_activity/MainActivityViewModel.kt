package com.example.room_demo.ui.components.main_activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.room_demo.data.CategoryItemRepository
import com.example.room_demo.data.CategoryRepository
import com.example.room_demo.ui.components.detail_form.FormViewModel
import androidx.lifecycle.viewModelScope
import com.example.room_demo.data.data_classes.Category
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val categoryItemRepository: CategoryItemRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    fun addCategory(category: Category) = viewModelScope.launch {
        categoryRepository.insertCategory(category)
    }
}

class MainViewModelFactory(
    private val categoryItemRepository: CategoryItemRepository,
    private val categoryRepository: CategoryRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(categoryItemRepository, categoryRepository) as T
        }
        throw IllegalAccessException("Unknown viewModel class")
    }
}