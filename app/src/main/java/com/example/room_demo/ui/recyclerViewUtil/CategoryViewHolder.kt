package com.example.room_demo.ui.recyclerViewUtil

import androidx.recyclerview.widget.RecyclerView
import com.example.room_demo.data.data_classes.Category
import com.example.room_demo.databinding.PreviewCardBinding

class CategoryViewHolder(private val binding: PreviewCardBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindCard(preview : Category) {
        binding.categoryName.text = preview.name
        binding.spent.text = preview.amountSpend.toString()
    }
}