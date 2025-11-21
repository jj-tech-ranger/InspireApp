package com.inspirewear.android.data.order

import com.inspirewear.android.data.models.Product

data class Order(
    val id: Long,
    val customerName: String,
    val total: Double,
    val products: List<Product>
)