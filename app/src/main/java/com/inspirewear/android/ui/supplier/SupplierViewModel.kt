package com.inspirewear.android.ui.supplier

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inspirewear.android.data.supplier.Supplier
import com.inspirewear.android.data.supplier.SupplierRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SupplierViewModel : ViewModel() {

    private val repository = SupplierRepository()

    private val _suppliers = MutableStateFlow<List<Supplier>>(emptyList())
    val suppliers: StateFlow<List<Supplier>> = _suppliers

    fun getSuppliers() {
        viewModelScope.launch {
            _suppliers.value = repository.getSuppliers()
        }
    }

    fun addSupplier(supplier: Supplier) {
        viewModelScope.launch {
            repository.addSupplier(supplier)
            getSuppliers()
        }
    }

    fun updateSupplier(id: Long, supplier: Supplier) {
        viewModelScope.launch {
            repository.updateSupplier(id, supplier)
            getSuppliers()
        }
    }

    fun deleteSupplier(id: Long) {
        viewModelScope.launch {
            repository.deleteSupplier(id)
            getSuppliers()
        }
    }
}