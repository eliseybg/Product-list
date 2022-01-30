package com.breaktime.lab2.api

import com.breaktime.lab2.model.NewBooks
import retrofit2.Call
import retrofit2.http.GET

interface BookApi {
    @GET("new")
    fun getNewBooks(): Call<NewBooks>
}