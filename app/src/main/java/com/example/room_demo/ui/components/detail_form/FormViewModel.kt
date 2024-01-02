package com.example.room_demo.ui.components.detail_form

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.room_demo.data.CategoryItemRepository
import com.example.room_demo.data.CategoryRepository
import com.example.room_demo.data.data_classes.CategoryItems
import java.time.LocalDate

class FormViewModel(
    private val categoryItemRepository: CategoryItemRepository,
    private val categoryRepository: CategoryRepository
) : ViewModel() {
    val summary = MutableLiveData("")
    val categoryName = MutableLiveData("")
    val amountSpent = 0.0

    @RequiresApi(Build.VERSION_CODES.O)
    val date = MutableLiveData("${LocalDate.now()}")
    var selectedSpinnerPosition: Int = 0

    val categoryNames = categoryRepository.categoryNames
    suspend fun insertData(categoryItem: CategoryItems) {
        categoryItemRepository.insert(categoryItem)
    }
}

class FormViewModelFactory(
    private val categoryItemRepository: CategoryItemRepository,
    private val categoryRepository: CategoryRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FormViewModel::class.java)) {
            return FormViewModel(categoryItemRepository, categoryRepository) as T
        }
        throw IllegalAccessException("Unknown viewModel class")
    }
}