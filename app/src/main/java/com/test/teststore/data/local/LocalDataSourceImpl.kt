package com.test.teststore.data.local

import com.test.teststore.domain.interfaces.LocalDataSource
import com.test.teststore.domain.models.Product
import javax.inject.Inject
import javax.inject.Singleton

class LocalDataSourceImpl @Inject constructor(
    private val storeDao: StoreDao
) : LocalDataSource {
    override suspend fun insertProduct(product: Product) {
        storeDao.insertProduct(
            ProductEntity(
                id = product.id,
                title = product.title,
                price = product.price,
                description = product.description,
                category = product.category,
                image = product.image
            )
        )
    }

    override suspend fun deleteProductById(productId: Int) {
        storeDao.deleteProductById(productId)
    }

    override suspend fun getProductById(productId: Int): Product? {
        return storeDao.getProductById(productId)?.toProduct()
    }

    override suspend fun getAllProducts(): List<Product> {
        return storeDao.getAllProducts().map { it.toProduct() }
    }
}