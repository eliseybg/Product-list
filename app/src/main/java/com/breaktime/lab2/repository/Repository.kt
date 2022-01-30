package com.breaktime.lab2.repository

import com.breaktime.lab2.api.RetrofitInstance

class Repository {
    fun getNewBooks() = RetrofitInstance.api.getNewBooks()
}