package com.inspirewear.android.ui.customer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inspirewear.android.data.customer.Customer
import com.inspirewear.android.data.customer.CustomerRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CustomerViewModel : ViewModel() {

    private val repository = CustomerRepository()

    private var originalCustomers = listOf<Customer>()
    private val _customers = MutableStateFlow<List<Customer>>(emptyList())
    val customers: StateFlow<List<Customer>> = _customers

    private val _selectedCustomer = MutableStateFlow<Customer?>(null)
    val selectedCustomer: StateFlow<Customer?> = _selectedCustomer

    fun getCustomers() {
        viewModelScope.launch {
            originalCustomers = repository.getCustomers()
            _customers.value = originalCustomers
        }
    }

    fun getCustomerById(id: Long) {
        viewModelScope.launch {
            _selectedCustomer.value = originalCustomers.find { it.id == id }
        }
    }

    fun addCustomer(customer: Customer) {
        viewModelScope.launch {
            repository.addCustomer(customer)
            getCustomers()
        }
    }

    fun updateCustomer(id: Long, customer: Customer) {
        viewModelScope.launch {
            repository.updateCustomer(id, customer)
            getCustomers()
        }
    }

    fun deleteCustomer(id: Long) {
        viewModelScope.launch {
            repository.deleteCustomer(id)
            getCustomers()
        }
    }

    fun filterCustomers(query: String) {
        _customers.value = if (query.isEmpty()) {
            originalCustomers
        } else {
            originalCustomers.filter { "${it.first_name} ${it.last_name}".contains(query, ignoreCase = true) }
        }
    }

    fun sortCustomers(sortOption: String) {
        _customers.value = when (sortOption) {
            "Name" -> _customers.value.sortedBy { it.first_name }
            "Location" -> _customers.value.sortedBy { it.location }
            else -> _customers.value
        }
    }
}