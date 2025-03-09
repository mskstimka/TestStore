package com.test.teststore.data.utils

import com.test.teststore.data.models.ProductResponse
import com.test.teststore.domain.models.Product

fun ProductResponse.toProduct(): Product = Product(
    id = this.id ?: 0,
    title = this.title ?: "No Title",
    price = this.price ?: 0.0,
    description = this.description ?: "No Description",
    category = this.category ?: "No Category",
    image = this.image,
)

fun List<ProductResponse>.toListProduct(): List<Product> =
    if (this.isNotEmpty()) {
        this.map {
            it.toProduct()
        }
    } else {
        emptyList()
    }

