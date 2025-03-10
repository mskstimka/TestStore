package com.test.teststore.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.test.teststore.domain.models.Product

@Entity(tableName = "products")
data class ProductEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String?
) {
    fun toProduct() = Product(id, title, price, description, category, image)
}