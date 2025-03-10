package com.test.teststore.domain.usecases

import com.test.teststore.domain.interfaces.StoreRepository
import com.test.teststore.domain.models.Product
import javax.inject.Inject

class GetLocalAllProductsUseCase @Inject constructor(
    private val storeRepository: StoreRepository
) {

    suspend fun execute(): List<Product> = storeRepository.getAllLocalProducts()
}