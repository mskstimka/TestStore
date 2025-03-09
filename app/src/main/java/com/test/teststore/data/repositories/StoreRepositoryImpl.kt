package com.test.teststore.data.repositories

import com.test.teststore.domain.interfaces.NetworkDataSource
import com.test.teststore.domain.interfaces.StoreRepository
import com.test.teststore.domain.models.Product
import com.test.teststore.domain.models.Results
import javax.inject.Inject

class StoreRepositoryImpl @Inject constructor(
    private val networkDataSource: NetworkDataSource
) : StoreRepository {

    override suspend fun getAllProducts(): Results<List<Product>> =
        networkDataSource.getAllProducts()

    override fun getDetailsOfProduct(id: Int): Product {
        TODO("Not yet implemented")
    }

    override fun saveProductToLocal(product: Product) {
        TODO("Not yet implemented")
    }

    override fun getLocalProducts(): List<Product> {
        TODO("Not yet implemented")
    }
}