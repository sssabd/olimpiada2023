package com.example.domain.usecases

import androidx.lifecycle.LiveData
import com.example.domain.models.Item
import com.example.domain.repository.ServicesRepository


class ListServicesObserverUseCase(private val servicesRepository: ServicesRepository) {
    operator fun invoke(): LiveData<List<Item>?> {
        return servicesRepository.servicesObserver()
    }
}