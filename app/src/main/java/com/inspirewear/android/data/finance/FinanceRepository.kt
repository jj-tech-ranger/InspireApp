package com.inspirewear.android.data.finance

import com.inspirewear.android.network.RetrofitClient

class FinanceRepository {
    suspend fun getTransactions(): List<Transaction> {
        return RetrofitClient.apiService.getTransactions().transactions
    }

    suspend fun addTransaction(transaction: Transaction): Transaction {
        return RetrofitClient.apiService.addTransaction(transaction)
    }

    suspend fun updateTransaction(id: Long, transaction: Transaction): Transaction {
        return RetrofitClient.apiService.updateTransaction(id, transaction)
    }

    suspend fun deleteTransaction(id: Long) {
        RetrofitClient.apiService.deleteTransaction(id)
    }
}