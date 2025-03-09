package com.test.teststore.domain.interfaces

import com.test.teststore.domain.models.Product
import com.test.teststore.domain.models.Results

interface StoreRepository {
    suspend fun getAllProducts(): Results<List<Product>>
    suspend fun getDetailsOfProduct(id: Int): Results<Product>
    fun saveProductToLocal(product: Product)
    fun getLocalProducts(): List<Product>
}