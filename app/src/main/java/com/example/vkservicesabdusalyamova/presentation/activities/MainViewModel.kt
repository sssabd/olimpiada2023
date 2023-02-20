package com.example.vkservicesabdusalyamova.presentation.activities

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.data.repositoryimpl.ServicesRepositoryImpl
import com.example.domain.models.Item
import com.example.domain.usecases.ListServicesObserverUseCase
import com.example.domain.usecases.UpdateServicesUseCase

class MainViewModel() : ViewModel() {

    // Поскольку сторонние библиотеки подключать запрещено, то внедрять di нет возможности
    // Реализация будем напрямую без зависимостей di (Koin, Dagger, Hilt..)

    private val servicesRepositoryImpl = ServicesRepositoryImpl()
    private val updateServicesUseCase = UpdateServicesUseCase(servicesRepositoryImpl)
    private val listServicesObserverUseCase = ListServicesObserverUseCase(servicesRepositoryImpl)

    private val _listServicesLiveData: LiveData<List<Item>?> = listServicesObserverUseCase()
    val listServicesLiveData: LiveData<List<Item>?>
        get() = _listServicesLiveData


    fun getListServices() {
        updateServicesUseCase()
    }

}