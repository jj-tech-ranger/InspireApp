package com.inspirewear.android.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inspirewear.android.data.ProductRepository
import com.inspirewear.android.data.models.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductViewModel : ViewModel() {

    private val repository = ProductRepository()

    private var originalProducts = listOf<Product>()
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    private val _selectedProduct = MutableStateFlow<Product?>(null)
    val selectedProduct: StateFlow<Product?> = _selectedProduct

    fun getProducts() {
        viewModelScope.launch {
            originalProducts = repository.getProducts()
            _products.value = originalProducts
        }
    }

    fun getProductById(id: Long) {
        viewModelScope.launch {
            _selectedProduct.value = originalProducts.find { it.id == id }
        }
    }

    fun addProduct(product: Product) {
        viewModelScope.launch {
            repository.addProduct(product)
            getProducts()
        }
    }

    fun updateProduct(id: Long, product: Product) {
        viewModelScope.launch {
            repository.updateProduct(id, product)
            getProducts()
        }
    }

    fun deleteProduct(id: Long) {
        viewModelScope.launch {
            repository.deleteProduct(id)
            getProducts()
        }
    }

    fun filterProducts(query: String) {
        _products.value = if (query.isEmpty()) {
            originalProducts
        } else {
            originalProducts.filter { it.name.contains(query, ignoreCase = true) }
        }
    }

    fun sortProducts(sortOption: String) {
        _products.value = when (sortOption) {
            "Name" -> _products.value.sortedBy { it.name }
            "Price" -> _products.value.sortedBy { it.price }
            else -> _products.value
        }
    }
}