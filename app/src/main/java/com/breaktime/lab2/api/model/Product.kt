package com.breaktime.lab2.api.model

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.google.gson.annotations.Expose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.net.URL

data class Product(
    val id: Long,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rating: Rating,
    @Expose
    val isInStock: Boolean = true,
    @Expose
    var isVisible: Boolean = false
) {
    var bitmap: Flow<Bitmap?> = flow {
        val url = URL(image)
        val bmp =
            BitmapFactory.decodeStream(url.openConnection().getInputStream())
        emit(bmp)
    }.flowOn(kotlinx.coroutines.Dispatchers.IO)
}