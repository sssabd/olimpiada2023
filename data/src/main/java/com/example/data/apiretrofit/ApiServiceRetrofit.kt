package com.example.data.apiretrofit


import com.example.domain.models.ListOfItems
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiServiceRetrofit {
    @GET("semi-final-data.json")
    fun getItems(): Call<ListOfItems>

    companion object {
        private const val URL = "https://mobile-olympiad-trajectory.hb.bizmrg.com/"
        fun create(): ApiServiceRetrofit {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(URL)
                .build()
            return retrofit.create(ApiServiceRetrofit::class.java)
        }
    }

}