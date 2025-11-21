package com.inspirewear.android.ui.product

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.inspirewear.android.data.models.Product

@Composable
fun ProductListScreen(viewModel: ProductViewModel = viewModel(), navController: NavController) {
    val products by viewModel.products.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var selectedProduct by remember { mutableStateOf<Product?>(null) }
    var searchQuery by remember { mutableStateOf("") }
    var sortOption by remember { mutableStateOf("Name") }

    viewModel.getProducts()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                selectedProduct = null
                showDialog = true
            }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Product")
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it; viewModel.filterProducts(it) },
                    label = { Text("Search") },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                SortDropDown(sortOption) { newSortOption ->
                    sortOption = newSortOption
                    viewModel.sortProducts(newSortOption)
                }
            }
            LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(products) { product ->
                    ProductItem(
                        product = product,
                        onEditClick = {
                            selectedProduct = product
                            showDialog = true
                        },
                        onDeleteClick = { viewModel.deleteProduct(product.id) },
                        onItemClick = { navController.navigate("productDetail/${product.id}") }
                    )
                }
            }
        }
    }

    if (showDialog) {
        ProductFormDialog(
            product = selectedProduct,
            onDismiss = { showDialog = false },
            onSave = { product ->
                if (selectedProduct == null) {
                    viewModel.addProduct(product)
                } else {
                    viewModel.updateProduct(product.id, product)
                }
                showDialog = false
            }
        )
    }
}

@Composable
fun SortDropDown(sortOption: String, onSortOptionSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val sortOptions = listOf("Name", "Price")

    Box {
        Button(onClick = { expanded = true }) {
            Text(text = sortOption)
            Icon(Icons.Filled.ArrowDropDown, contentDescription = "Sort")
        }
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            sortOptions.forEach { option ->
                DropdownMenuItem(text = { Text(option) }, onClick = { onSortOptionSelected(option); expanded = false })
            }
        }
    }
}

@Composable
fun ProductItem(
    product: Product, 
    onEditClick: () -> Unit, 
    onDeleteClick: () -> Unit, 
    onItemClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onItemClick),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = product.name, style = MaterialTheme.typography.titleMedium)
                Text(text = product.description, style = MaterialTheme.typography.bodyMedium)
                Text(text = "$${product.price}", style = MaterialTheme.typography.bodyLarge)
            }
            Row {
                IconButton(onClick = onEditClick) {
                    Icon(Icons.Filled.Edit, contentDescription = "Edit")
                }
                IconButton(onClick = onDeleteClick) {
                    Icon(Icons.Filled.Delete, contentDescription = "Delete")
                }
            }
        }
    }
}