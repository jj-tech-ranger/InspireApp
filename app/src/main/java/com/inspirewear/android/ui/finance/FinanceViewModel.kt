package com.inspirewear.android.ui.finance

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inspirewear.android.data.finance.FinanceRepository
import com.inspirewear.android.data.finance.Transaction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FinanceViewModel : ViewModel() {

    private val repository = FinanceRepository()

    private val _transactions = MutableStateFlow<List<Transaction>>(emptyList())
    val transactions: StateFlow<List<Transaction>> = _transactions

    fun getTransactions() {
        viewModelScope.launch {
            _transactions.value = repository.getTransactions()
        }
    }

    fun addTransaction(transaction: Transaction) {
        viewModelScope.launch {
            repository.addTransaction(transaction)
            getTransactions()
        }
    }

    fun updateTransaction(id: Long, transaction: Transaction) {
        viewModelScope.launch {
            repository.updateTransaction(id, transaction)
            getTransactions()
        }
    }

    fun deleteTransaction(id: Long) {
        viewModelScope.launch {
            repository.deleteTransaction(id)
            getTransactions()
        }
    }
}