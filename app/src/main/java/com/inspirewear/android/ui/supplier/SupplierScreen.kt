package com.inspirewear.android.ui.supplier

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.inspirewear.android.data.supplier.Supplier

@Composable
fun SupplierListScreen(viewModel: SupplierViewModel = viewModel()) {
    val suppliers by viewModel.suppliers.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var selectedSupplier by remember { mutableStateOf<Supplier?>(null) }

    viewModel.getSuppliers()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true; selectedSupplier = null }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Supplier")
            }
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues), contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(suppliers) { supplier ->
                SupplierItem(
                    supplier = supplier,
                    onEditClick = { showDialog = true; selectedSupplier = supplier },
                    onDeleteClick = { viewModel.deleteSupplier(supplier.id) },
                    onItemClick = {}
                )
            }
        }
    }

    if (showDialog) {
        SupplierFormDialog(
            supplier = selectedSupplier,
            onDismiss = { showDialog = false },
            onSave = {
                if (selectedSupplier == null) {
                    viewModel.addSupplier(it)
                } else {
                    viewModel.updateSupplier(it.id, it)
                }
                showDialog = false
            }
        )
    }
}

@Composable
fun SupplierItem(supplier: Supplier, onEditClick: () -> Unit, onDeleteClick: () -> Unit, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onItemClick),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = supplier.name, style = MaterialTheme.typography.titleMedium)
                Text(text = "Contact: ${supplier.contact_person}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Phone: ${supplier.phone}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Email: ${supplier.email}", style = MaterialTheme.typography.bodyMedium)
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