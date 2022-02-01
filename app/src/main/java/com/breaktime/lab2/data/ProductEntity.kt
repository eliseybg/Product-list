package com.breaktime.lab2.data

import com.breaktime.lab2.api.model.Rating
import io.realm.RealmObject

data class ProductEntity(
    val id: Long,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rating,
    val isInStock: Boolean,
    val label: String = "Add label"
) : RealmObject()