package com.inspirewear.android.data.inventory

import com.inspirewear.android.network.RetrofitClient

class InventoryRepository {
    suspend fun getInventory(): List<InventoryItem> {
        return RetrofitClient.apiService.getInventory().inventory
    }

    suspend fun addInventoryItem(item: InventoryItem): InventoryItem {
        return RetrofitClient.apiService.addInventoryItem(item)
    }

    suspend fun updateInventoryItem(id: Long, item: InventoryItem): InventoryItem {
        return RetrofitClient.apiService.updateInventoryItem(id, item)
    }

    suspend fun deleteInventoryItem(id: Long) {
        RetrofitClient.apiService.deleteInventoryItem(id)
    }
}