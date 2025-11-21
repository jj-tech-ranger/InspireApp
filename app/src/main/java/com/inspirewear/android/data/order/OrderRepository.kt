package com.inspirewear.android.data.order

import com.inspirewear.android.network.RetrofitClient

class OrderRepository {
    suspend fun getOrders(): List<Order> {
        return RetrofitClient.apiService.getOrders().orders
    }

    suspend fun addOrder(order: Order): Order {
        return RetrofitClient.apiService.addOrder(order)
    }

    suspend fun updateOrder(id: Long, order: Order): Order {
        return RetrofitClient.apiService.updateOrder(id, order)
    }

    suspend fun deleteOrder(id: Long) {
        RetrofitClient.apiService.deleteOrder(id)
    }
}