package com.inspirewear.android.ui.customer

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
import com.inspirewear.android.data.customer.Customer

@Composable
fun CustomerListScreen(viewModel: CustomerViewModel = viewModel(), navController: NavController) {
    val customers by viewModel.customers.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var selectedCustomer by remember { mutableStateOf<Customer?>(null) }
    var searchQuery by remember { mutableStateOf("") }
    var sortOption by remember { mutableStateOf("Name") }

    viewModel.getCustomers()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true; selectedCustomer = null }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Customer")
            }
        }
    ) {
        Column(modifier = Modifier.padding(it)) {
            Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it; viewModel.filterCustomers(it) },
                    label = { Text("Search") },
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(8.dp))
                SortDropDown(sortOption) { newSortOption ->
                    sortOption = newSortOption
                    viewModel.sortCustomers(newSortOption)
                }
            }
            LazyColumn(contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
                items(customers) { customer ->
                    CustomerItem(
                        customer = customer,
                        onEditClick = { showDialog = true; selectedCustomer = customer },
                        onDeleteClick = { viewModel.deleteCustomer(customer.id) },
                        onItemClick = { navController.navigate("customerDetail/${customer.id}") }
                    )
                }
            }
        }
    }

    if (showDialog) {
        CustomerFormDialog(
            customer = selectedCustomer,
            onDismiss = { showDialog = false },
            onSave = {
                if (selectedCustomer == null) {
                    viewModel.addCustomer(it)
                } else {
                    viewModel.updateCustomer(it.id, it)
                }
                showDialog = false
            }
        )
    }
}

@Composable
fun SortDropDown(sortOption: String, onSortOptionSelected: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val sortOptions = listOf("Name", "Location")

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
fun CustomerItem(customer: Customer, onEditClick: () -> Unit, onDeleteClick: () -> Unit, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onItemClick),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = "${customer.first_name} ${customer.last_name}", style = MaterialTheme.typography.titleMedium)
                Text(text = customer.email, style = MaterialTheme.typography.bodyMedium)
                Text(text = customer.phone, style = MaterialTheme.typography.bodyMedium)
                Text(text = customer.location, style = MaterialTheme.typography.bodyMedium)
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