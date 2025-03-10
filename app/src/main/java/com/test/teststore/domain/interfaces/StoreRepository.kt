package com.test.teststore.domain.interfaces

import com.test.teststore.domain.models.Product
import com.test.teststore.domain.models.Results

interface StoreRepository {
    suspend fun getAllProducts(): Results<List<Product>>

    suspend fun getDetailsOfProduct(id: Int): Results<Product>

    suspend fun insertProduct(product: Product)

    suspend fun deleteProductById(productId: Int)

    suspend fun getProductById(productId: Int): Product?

    suspend fun getAllLocalProducts(): List<Product>
}