package com.test.teststore.data.repositories

import com.test.teststore.domain.interfaces.LocalDataSource
import com.test.teststore.domain.interfaces.NetworkDataSource
import com.test.teststore.domain.interfaces.StoreRepository
import com.test.teststore.domain.models.Product
import com.test.teststore.domain.models.Results
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: LocalDataSource
) : StoreRepository {

    override suspend fun getAllProducts(): Results<List<Product>> =
        networkDataSource.getAllProducts()

    override suspend fun getDetailsOfProduct(id: Int): Results<Product> =
        networkDataSource.getDetailsOfProduct(id = id)

    override suspend fun insertProduct(product: Product) {
        localDataSource.insertProduct(product = product)
    }

    override suspend fun deleteProductById(productId: Int) {
        localDataSource.deleteProductById(productId = productId)
    }

    override suspend fun getProductById(productId: Int): Product? =
        localDataSource.getProductById(productId = productId)

    override suspend fun getAllLocalProducts(): List<Product> = localDataSource.getAllProducts()


}