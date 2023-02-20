package com.example.vkservicesabdusalyamova.presentation.recyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.vkservicesabdusalyamova.R

class ServiceViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val tvNameService: TextView = view.findViewById(R.id.tv_name_service)
    val ivIconService: ImageView = view.findViewById(R.id.iv_icon_service)
    val layout: ConstraintLayout = view.findViewById(R.id.item_service_layout)
}