package com.inspirewear.android.data.supplier

import com.inspirewear.android.network.RetrofitClient

class SupplierRepository {
    suspend fun getSuppliers(): List<Supplier> {
        return RetrofitClient.apiService.getSuppliers().suppliers
    }

    suspend fun addSupplier(supplier: Supplier): Supplier {
        return RetrofitClient.apiService.addSupplier(supplier)
    }

    suspend fun updateSupplier(id: Long, supplier: Supplier): Supplier {
        return RetrofitClient.apiService.updateSupplier(id, supplier)
    }

    suspend fun deleteSupplier(id: Long) {
        RetrofitClient.apiService.deleteSupplier(id)
    }
}