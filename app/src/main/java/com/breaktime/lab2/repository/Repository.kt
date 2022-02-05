package com.breaktime.lab2.repository

import androidx.lifecycle.MutableLiveData
import com.breaktime.lab2.api.ProductApi
import com.breaktime.lab2.api.model.Product
import com.breaktime.lab2.data.FavoriteProductDao
import com.breaktime.lab2.data.ProductEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlin.random.Random

class Repository(private var productApi: ProductApi, private var productDao: FavoriteProductDao) {
    val allProducts: Flow<List<Product>?> = getFlowProducts()
    val allCategories: Flow<List<String>?> = getFlowCategories()
    val favoriteProducts = MutableLiveData<List<String>?>()
    val favoriteCategories = MutableLiveData<List<String>?>()

    private fun getFlowProducts(): Flow<List<Product>?> {
        return flow {
            try {
                val fooList =
                    productApi.getProducts().map {
                        it.copy(isInStock = Random.nextBoolean())
                    }
                emit(fooList)
            } catch (e: Exception) {
                println("error   $e")
                emit(null)
            }
        }.flowOn(Dispatchers.IO)
    }

    private fun getFlowCategories(): Flow<List<String>?> {
        return flow {
            try {
                val fooList = productApi.getCategories()
                emit(fooList)
            } catch (e: Exception) {
                println("error   $e")
                emit(null)
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getFavoriteProducts(): List<ProductEntity> {
        val list = productDao.readAllData()
        favoriteCategories.value = list.map { it.category }.distinct()
        return list
    }

    fun addFavorite(productEntity: ProductEntity) {
        productDao.addProduct(productEntity)
    }

    fun deleteFavorite(productEntity: ProductEntity) {
        productDao.deleteProduct(productEntity)
    }

    fun updateFavorite(productEntity: ProductEntity) {
        productDao.updateProduct(productEntity)
    }

    suspend fun setup() {
        allProducts.onEach { list ->
            list?.forEach { product ->
                product.bitmap.collect()
            }
        }
    }
}