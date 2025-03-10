package com.test.teststore.domain.usecases

import com.test.teststore.domain.interfaces.StoreRepository
import com.test.teststore.domain.models.Product
import javax.inject.Inject

class LocalInsertProductUseCase @Inject constructor(
    private val storeRepository: StoreRepository
) {
    suspend fun execute(product: Product) {
        storeRepository.insertProduct(product = product)
    }
}