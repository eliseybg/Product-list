package com.breaktime.lab2.api.model

import com.google.gson.annotations.Expose
import kotlin.random.Random

data class Product(
    val id: Long,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rating,
    @Expose
    val isInStock: Boolean = Random.nextBoolean()
)