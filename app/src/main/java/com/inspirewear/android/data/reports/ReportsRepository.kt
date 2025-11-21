package com.inspirewear.android.data.reports

import com.inspirewear.android.network.RetrofitClient

class ReportsRepository {
    suspend fun getCustomerReport(): CustomerReport {
        return RetrofitClient.apiService.getCustomerReport()
    }
}