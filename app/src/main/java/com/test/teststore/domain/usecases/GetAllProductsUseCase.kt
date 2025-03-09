package com.test.teststore.domain.usecases

import com.test.teststore.domain.interfaces.StoreRepository
import com.test.teststore.domain.models.Product
import com.test.teststore.domain.models.Results
import javax.inject.Inject

class GetAllProductsUseCase @Inject constructor(
    private val storeRepository: StoreRepository
) {
    suspend fun execute() : Results<List<Product>> = storeRepository.getAllProducts()
}