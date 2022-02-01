package com.breaktime.lab2.model

data class Product(
    val id: Long,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    @com.google.gson.annotations.Expose
    val rating: Rating,
)