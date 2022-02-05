package com.breaktime.lab2.util

import android.graphics.Bitmap
import com.breaktime.lab2.api.model.Product
import com.breaktime.lab2.data.ProductEntity
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

fun Product.toProductEntity(): ProductEntity {
    var bitmap: Bitmap? = null
    this.bitmap.onEach { bitmap = it }.launchIn(MainScope())
    return ProductEntity(
        id,
        title,
        price,
        description,
        category,
        bitmap?.toByteArray(),
        rating.rate,
        isInStock
    )
}