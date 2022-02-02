package com.breaktime.lab2.repository

import com.breaktime.lab2.api.RetrofitInstance
import com.breaktime.lab2.api.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.random.Random

class Repository {
    val allProducts: Flow<List<Product>?> = getFlowProducts()
    private fun getFlowProducts(): Flow<List<Product>?> {
        return flow {
            try {
                val fooList = RetrofitInstance.api.getProducts().map {
                    it.copy(isInStock = Random.nextBoolean())
                }
                emit(fooList)
            } catch (e: Exception) {
                println("error   $e")
                emit(null)
            }
        }.flowOn(Dispatchers.IO)
    }

    var allCategories: Flow<List<String>?> = getFlowCategories()
    private fun getFlowCategories(): Flow<List<String>?> {
        return flow {
            try {
                val fooList = RetrofitInstance.api.getCategories()
                emit(fooList)
            } catch (e: Exception) {
                println("error   $e")
                emit(null)
            }
        }.flowOn(Dispatchers.IO)
    }
}