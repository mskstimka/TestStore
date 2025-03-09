package com.test.teststore.data.network

import com.test.teststore.data.models.ProductResponse
import retrofit2.Response
import retrofit2.http.GET

interface StoreAPI {

    @GET("/products")
    suspend fun getAllProducts(): Response<List<ProductResponse>>
}