package com.inspirewear.android.ui.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inspirewear.android.data.order.Order
import com.inspirewear.android.data.order.OrderRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OrderViewModel : ViewModel() {

    private val repository = OrderRepository()

    private var originalOrders = listOf<Order>()
    private val _orders = MutableStateFlow<List<Order>>(emptyList())
    val orders: StateFlow<List<Order>> = _orders

    private val _selectedOrder = MutableStateFlow<Order?>(null)
    val selectedOrder: StateFlow<Order?> = _selectedOrder

    fun getOrders() {
        viewModelScope.launch {
            originalOrders = repository.getOrders()
            _orders.value = originalOrders
        }
    }

    fun getOrderById(id: Long) {
        viewModelScope.launch {
            _selectedOrder.value = originalOrders.find { it.id == id }
        }
    }

    fun addOrder(order: Order) {
        viewModelScope.launch {
            repository.addOrder(order)
            getOrders()
        }
    }

    fun updateOrder(id: Long, order: Order) {
        viewModelScope.launch {
            repository.updateOrder(id, order)
            getOrders()
        }
    }

    fun deleteOrder(id: Long) {
        viewModelScope.launch {
            repository.deleteOrder(id)
            getOrders()
        }
    }

    fun filterOrders(query: String) {
        _orders.value = if (query.isEmpty()) {
            originalOrders
        } else {
            originalOrders.filter { it.customerName.contains(query, ignoreCase = true) }
        }
    }

    fun sortOrders(sortOption: String) {
        _orders.value = when (sortOption) {
            "Customer" -> _orders.value.sortedBy { it.customerName }
            "Total" -> _orders.value.sortedBy { it.total }
            else -> _orders.value
        }
    }
}