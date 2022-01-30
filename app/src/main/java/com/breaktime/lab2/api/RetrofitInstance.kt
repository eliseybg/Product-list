package com.breaktime.lab2.api

import com.breaktime.lab2.Util.Companion.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: BookApi by lazy {
        retrofit.create(BookApi::class.java)
    }
}