package com.example.data.repositoryimpl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import com.example.data.apiretrofit.ApiServiceRetrofit
import com.example.domain.models.Item
import com.example.domain.models.ListOfItems
import com.example.domain.repository.ServicesRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServicesRepositoryImpl : ServicesRepository {

    private var servicesLiveData = MutableLiveData<List<Item>?>()

    override fun updateServices() {
        val apiServiceRetrofit = ApiServiceRetrofit.create().getItems()
        var items: List<Item>? = null

        apiServiceRetrofit.enqueue(object : Callback<ListOfItems> {
            override fun onResponse(call: Call<ListOfItems>, response: Response<ListOfItems>) {
                servicesLiveData.value = response.body()?.items
            }
            override fun onFailure(call: Call<ListOfItems>, t: Throwable) {
                servicesLiveData.value = null
            }
        })
    }

    override fun servicesObserver(): LiveData<List<Item>?> {
        return servicesLiveData
    }
}