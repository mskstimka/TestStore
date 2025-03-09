package com.test.teststore.domain.interfaces

import com.test.teststore.domain.models.Product
import com.test.teststore.domain.models.Results

interface StoreRepository {
    suspend fun getAllProducts(): Results<List<Product>>
    fun getDetailsOfProduct(id: Int): Product
    fun saveProductToLocal(product: Product)
    fun getLocalProducts(): List<Product>
}