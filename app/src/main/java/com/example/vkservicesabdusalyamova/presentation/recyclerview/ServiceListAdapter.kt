package com.example.vkservicesabdusalyamova.presentation.recyclerview


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.domain.models.Item
import com.example.vkservicesabdusalyamova.R
import com.squareup.picasso.Picasso


class ServiceListAdapter : ListAdapter<Item, ServiceViewHolder>(ServiceDiffUtilCallback()) {

    var showDetailsAboutTheService: ((item: Item) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.item_service,
            parent,
            false
        )
        return ServiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        val service = getItem(position)
        with(holder) {
            tvNameService.text = service.name
            Picasso.get().load(service.icon_url).into(ivIconService)
            layout.setOnClickListener {
                showDetailsAboutTheService?.invoke(Item(
                    description = service.description,
                    name = service.name,
                    icon_url = service.icon_url,
                    service_url = service.service_url,
                ))
            }
        }
    }


}