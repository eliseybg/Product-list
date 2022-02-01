package com.breaktime.lab2.api

import com.breaktime.lab2.model.Product
import retrofit2.Call
import retrofit2.http.GET

interface BookApi {
    @GET("products")
    fun getProducts(): Call<List<Product>>

    @GET("products/categories")
    fun getCategories(): Call<List<String>>
}