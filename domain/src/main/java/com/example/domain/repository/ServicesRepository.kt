package com.example.domain.repository

import androidx.lifecycle.LiveData
import com.example.domain.models.Item

interface ServicesRepository {
    fun updateServices()
    fun servicesObserver(): LiveData<List<Item>?>
}