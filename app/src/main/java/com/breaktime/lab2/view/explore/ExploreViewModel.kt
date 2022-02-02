package com.breaktime.lab2.view.explore

import androidx.lifecycle.ViewModel
import com.breaktime.lab2.api.model.Product
import com.breaktime.lab2.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ExploreViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    fun getAllProducts(): Flow<List<Product>?> {
        println("repository = $repository")
        var res = repository.allProducts
        println("res    $res")
        return res
    }

}