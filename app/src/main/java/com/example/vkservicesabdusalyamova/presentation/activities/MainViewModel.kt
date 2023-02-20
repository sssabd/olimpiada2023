package com.example.vkservicesabdusalyamova.presentation.activities

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.data.repositoryimpl.ServicesRepositoryImpl
import com.example.domain.models.Item
import com.example.domain.usecases.ServicesObserverUseCase
import com.example.domain.usecases.UpdateServicesUseCase

class MainViewModel() : ViewModel() {

    private val servicesRepositoryImpl = ServicesRepositoryImpl()
    private val updateServicesUseCase = UpdateServicesUseCase(servicesRepositoryImpl)
    private val servicesObserverUseCase = ServicesObserverUseCase(servicesRepositoryImpl)
    private val _listServicesLiveData: LiveData<List<Item>?> = servicesObserverUseCase()
    val listServicesLiveData: LiveData<List<Item>?>
        get() = _listServicesLiveData

    fun getListServices() {
        updateServicesUseCase()
    }

}