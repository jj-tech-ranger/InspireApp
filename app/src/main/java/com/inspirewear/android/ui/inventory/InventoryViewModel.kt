package com.inspirewear.android.ui.inventory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inspirewear.android.data.inventory.InventoryItem
import com.inspirewear.android.data.inventory.InventoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class InventoryViewModel : ViewModel() {

    private val repository = InventoryRepository()

    private val _inventory = MutableStateFlow<List<InventoryItem>>(emptyList())
    val inventory: StateFlow<List<InventoryItem>> = _inventory

    fun getInventory() {
        viewModelScope.launch {
            _inventory.value = repository.getInventory()
        }
    }

    fun addInventoryItem(item: InventoryItem) {
        viewModelScope.launch {
            repository.addInventoryItem(item)
            getInventory()
        }
    }

    fun updateInventoryItem(id: Long, item: InventoryItem) {
        viewModelScope.launch {
            repository.updateInventoryItem(id, item)
            getInventory()
        }
    }

    fun deleteInventoryItem(id: Long) {
        viewModelScope.launch {
            repository.deleteInventoryItem(id)
            getInventory()
        }
    }
}