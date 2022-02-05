package com.breaktime.lab2.util

import com.breaktime.lab2.api.model.Product
import com.breaktime.lab2.data.ProductEntity

fun Product.toProductEntity(): ProductEntity {
    return ProductEntity(id, title, price, description, category, image, rating.rate, isInStock)
}