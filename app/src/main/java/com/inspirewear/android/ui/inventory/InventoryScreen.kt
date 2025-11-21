package com.inspirewear.android.ui.inventory

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
import com.inspirewear.android.data.inventory.InventoryItem

@Composable
fun InventoryListScreen(viewModel: InventoryViewModel = viewModel()) {
    val inventory by viewModel.inventory.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<InventoryItem?>(null) }

    viewModel.getInventory()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true; selectedItem = null }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Item")
            }
        }
    ) {
        LazyColumn(modifier = Modifier.padding(it), contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(inventory) { item ->
                InventoryItem(
                    item = item,
                    onEditClick = { showDialog = true; selectedItem = item },
                    onDeleteClick = { viewModel.deleteInventoryItem(item.id) },
                    onItemClick = {}
                )
            }
        }
    }

    if (showDialog) {
        InventoryFormDialog(
            item = selectedItem,
            onDismiss = { showDialog = false },
            onSave = {
                if (selectedItem == null) {
                    viewModel.addInventoryItem(it)
                } else {
                    viewModel.updateInventoryItem(it.id, it)
                }
                showDialog = false
            }
        )
    }
}

@Composable
fun InventoryItem(item: InventoryItem, onEditClick: () -> Unit, onDeleteClick: () -> Unit, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onItemClick),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = item.name, style = MaterialTheme.typography.titleMedium)
                Text(text = "Category: ${item.category}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Size: ${item.size}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Color: ${item.color}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Stock: ${item.stock}", style = MaterialTheme.typography.bodyMedium)
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