package com.example.vkservicesabdusalyamova.presentation.recyclerview

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.models.Item


class ServiceDiffUtilCallback: DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem == newItem
    }
}