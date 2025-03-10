package com.test.teststore.domain.interfaces

import com.test.teststore.domain.models.Product

interface LocalDataSource {

    suspend fun insertProduct(product: Product)

    suspend fun deleteProductById(productId: Int)

    suspend fun getProductById(productId: Int): Product?

    suspend fun getAllProducts(): List<Product>
}