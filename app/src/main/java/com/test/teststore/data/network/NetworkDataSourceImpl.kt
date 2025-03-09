package com.test.teststore.data.network

import com.test.teststore.data.utils.toListProduct
import com.test.teststore.data.utils.toProduct
import com.test.teststore.domain.interfaces.NetworkDataSource
import com.test.teststore.domain.models.Product
import com.test.teststore.domain.models.Results
import javax.inject.Inject

class NetworkDataSourceImpl @Inject constructor(
    private val storeApi: StoreAPI
) : NetworkDataSource {

    override suspend fun getAllProducts(): Results<List<Product>> {
        return try {

            val response = storeApi.getAllProducts()

            when (response.isSuccessful) {
                true -> {
                    val list = response.body()!!.toListProduct()

                    Results.Success(data = list)
                }

                false -> {
                    Results.Error(exception = Exception(response.message()))
                }
            }

        } catch (e: Exception) {
            Results.Error(exception = e)

        }
    }

    override suspend fun getDetailsOfProduct(id: Int): Results<Product> {
        return try {

            val response = storeApi.getDetailsOfProduct(id = id)

            when (response.isSuccessful) {
                true -> {
                    val product = response.body()!!.toProduct()

                    Results.Success(data = product)
                }

                false -> {
                    Results.Error(exception = Exception(response.message()))
                }
            }

        } catch (e: Exception) {
            Results.Error(exception = e)

        }
    }

}