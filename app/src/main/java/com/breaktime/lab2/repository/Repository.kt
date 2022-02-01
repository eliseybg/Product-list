package com.breaktime.lab2.repository

import com.breaktime.lab2.api.RetrofitInstance

class Repository {
    fun getProducts() = RetrofitInstance.api.getProducts()

    fun getCategories() = RetrofitInstance.api.getCategories()
}