package com.inspirewear.android.ui.order

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
import com.inspirewear.android.data.order.Order

@Composable
fun OrderListScreen(viewModel: OrderViewModel = viewModel(), navController: NavController) {
    val orders by viewModel.orders.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var selectedOrder by remember { mutableStateOf<Order?>(null) }
    var searchQuery by remember { mutableStateOf("") }
    var sortOption by remember { mutableStateOf("Customer") }

    viewModel.getOrders()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true; selectedOrder = null }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Order")
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it; viewModel.filterOrders(it) },
                    label = { Text("Search") },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                SortDropDown(sortOption) { newSortOption ->
                    sortOption = newSortOption
                    viewModel.sortOrders(newSortOption)
                }
            }
            LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(orders) { order ->
                    OrderItem(
                        order = order,
                        onEditClick = { showDialog = true; selectedOrder = order },
                        onDeleteClick = { viewModel.deleteOrder(order.id) },
                        onItemClick = { navController.navigate("orderDetail/${order.id}") }
                    )
                }
            }
        }
    }

    if (showDialog) {
        OrderFormDialog(
            order = selectedOrder,
            onDismiss = { showDialog = false },
            onSave = { order ->
                if (selectedOrder == null) {
                    viewModel.addOrder(order)
                } else {
                    viewModel.updateOrder(order.id, order)
                }
                showDialog = false
            }
        )
    }
}

@Composable
fun SortDropDown(sortOption: String, onSortOptionSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val sortOptions = listOf("Customer", "Total")

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
fun OrderItem(
    order: Order, 
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
                Text(text = "Order ID: ${order.id}", style = MaterialTheme.typography.titleMedium)
                Text(text = "Customer: ${order.customerName}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Total: $${order.total}", style = MaterialTheme.typography.bodyLarge)
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