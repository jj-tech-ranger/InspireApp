package com.inspirewear.android.data.customer

import com.inspirewear.android.network.RetrofitClient

class CustomerRepository {
    suspend fun getCustomers(): List<Customer> {
        return RetrofitClient.apiService.getCustomers().customers
    }

    suspend fun addCustomer(customer: Customer): Customer {
        return RetrofitClient.apiService.addCustomer(customer)
    }

    suspend fun updateCustomer(id: Long, customer: Customer): Customer {
        return RetrofitClient.apiService.updateCustomer(id, customer)
    }

    suspend fun deleteCustomer(id: Long) {
        RetrofitClient.apiService.deleteCustomer(id)
    }
}