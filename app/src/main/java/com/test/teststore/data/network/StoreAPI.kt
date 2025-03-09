package com.test.teststore.data.network

import com.test.teststore.data.models.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface StoreAPI {

    @GET("/products")
    suspend fun getAllProducts(): Response<List<ProductResponse>>

    @GET("/products/{id}")
    suspend fun getDetailsOfProduct(@Path("id") id: Int): Response<ProductResponse>

}