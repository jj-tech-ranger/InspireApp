package com.inspirewear.android.data

import com.inspirewear.android.data.models.Product
import com.inspirewear.android.network.RetrofitClient

class ProductRepository {
    suspend fun getProducts(): List<Product> {
        return RetrofitClient.apiService.getProducts().products
    }

    suspend fun addProduct(product: Product): Product {
        return RetrofitClient.apiService.addProduct(product)
    }

    suspend fun updateProduct(id: Long, product: Product): Product {
        return RetrofitClient.apiService.updateProduct(id, product)
    }

    suspend fun deleteProduct(id: Long) {
        RetrofitClient.apiService.deleteProduct(id)
    }
}