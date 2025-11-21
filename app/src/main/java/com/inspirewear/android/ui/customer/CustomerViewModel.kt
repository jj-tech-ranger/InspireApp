package com.inspirewear.android.ui.customer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inspirewear.android.data.customer.Customer
import com.inspirewear.android.data.customer.CustomerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CustomerViewModel : ViewModel() {

    private val repository = CustomerRepository()

    private val _customers = MutableStateFlow<List<Customer>>(emptyList())
    val customers: StateFlow<List<Customer>> = _customers.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _selectedCustomer = MutableStateFlow<Customer?>(null)
    val selectedCustomer: StateFlow<Customer?> = _selectedCustomer.asStateFlow()

    fun getCustomers() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val result = repository.getCustomers()
                _customers.value = result
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load customers: ${e.message}"
                _customers.value = emptyList()
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun getCustomerById(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val result = repository.getCustomerById(id)
                _selectedCustomer.value = result
            } catch (e: Exception) {
                _errorMessage.value = "Failed to load customer: ${e.message}"
                _selectedCustomer.value = null
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun createCustomer(customer: Customer) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                repository.createCustomer(customer)
                getCustomers() // Refresh list
            } catch (e: Exception) {
                _errorMessage.value = "Failed to create customer: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateCustomer(customer: Customer) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                repository.updateCustomer(customer)
                getCustomers() // Refresh list
            } catch (e: Exception) {
                _errorMessage.value = "Failed to update customer: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun deleteCustomer(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                repository.deleteCustomer(id)
                getCustomers() // Refresh list
            } catch (e: Exception) {
                _errorMessage.value = "Failed to delete customer: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }
}
