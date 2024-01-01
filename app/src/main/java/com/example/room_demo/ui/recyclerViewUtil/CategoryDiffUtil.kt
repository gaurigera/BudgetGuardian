package com.example.room_demo.ui.recyclerViewUtil

import androidx.recyclerview.widget.DiffUtil
import com.example.room_demo.data.data_classes.Category

object CategoryDiffUtil : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean {
        return oldItem.name == newItem.name
                && oldItem.amountSpend == newItem.amountSpend
                && oldItem.id == newItem.id
    }
}