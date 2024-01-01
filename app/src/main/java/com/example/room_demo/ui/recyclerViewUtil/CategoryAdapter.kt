package com.example.room_demo.ui.recyclerViewUtil

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.room_demo.data.data_classes.Category
import com.example.room_demo.databinding.PreviewCardBinding

class CategoryAdapter : ListAdapter<Category, CategoryViewHolder>(CategoryDiffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            PreviewCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val previewCard = getItem(position)
        holder.bindCard(previewCard)
    }
}