package com.inspirewear.android.ui.customer

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.inspirewear.android.data.customer.Customer
import com.inspirewear.android.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomerListScreen(
    viewModel: CustomerViewModel = viewModel(),
    navController: NavController
) {
    val customers by viewModel.customers.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()
    
    var showDialog by remember { mutableStateOf(false) }
    var selectedCustomer by remember { mutableStateOf<Customer?>(null) }
    var searchQuery by remember { mutableStateOf("") }
    var showDeleteDialog by remember { mutableStateOf(false) }
    var customerToDelete by remember { mutableStateOf<Customer?>(null) }

    LaunchedEffect(Unit) {
        viewModel.getCustomers()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Customers",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkNavy,
                    titleContentColor = White
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    selectedCustomer = null
                    showDialog = true
                },
                containerColor = PastelBlue,
                contentColor = DarkNavy
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Customer")
            }
        },
        containerColor = DarkNavy
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Search Bar
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    placeholder = { Text("Search customers...", color = LightGrey) },
                    leadingIcon = {
                        Icon(
                            Icons.Filled.Search,
                            contentDescription = "Search",
                            tint = PastelBlue
                        )
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = PastelBlue,
                        unfocusedBorderColor = BorderColor,
                        focusedTextColor = White,
                        unfocusedTextColor = White,
                        cursorColor = PastelBlue
                    ),
                    shape = RoundedCornerShape(12.dp)
                )

                // Loading State
                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            CircularProgressIndicator(
                                color = PastelBlue,
                                modifier = Modifier.size(48.dp)
                            )
                            Text(
                                "Loading customers...",
                                color = LightGrey,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
                // Error State
                else if (errorMessage != null) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Icon(
                                Icons.Filled.Error,
                                contentDescription = "Error",
                                tint = ErrorRed,
                                modifier = Modifier.size(64.dp)
                            )
                            Text(
                                errorMessage ?: "Unknown error",
                                color = ErrorRed,
                                style = MaterialTheme.typography.bodyLarge
                            )
                            Button(
                                onClick = { viewModel.getCustomers() },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = PastelBlue,
                                    contentColor = DarkNavy
                                )
                            ) {
                                Icon(Icons.Filled.Refresh, contentDescription = null)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Retry")
                            }
                        }
                    }
                }
                // Empty State
                else if (customers.isEmpty()) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Icon(
                                Icons.Filled.PersonAdd,
                                contentDescription = "No customers",
                                tint = LightGrey,
                                modifier = Modifier.size(64.dp)
                            )
                            Text(
                                "No customers yet",
                                color = LightGrey,
                                style = MaterialTheme.typography.headlineSmall
                            )
                            Text(
                                "Tap the + button to add your first customer",
                                color = LightGrey,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
                // Customer List
                else {
                    val filteredCustomers = customers.filter {
                        it.name.contains(searchQuery, ignoreCase = true) ||
                        it.email.contains(searchQuery, ignoreCase = true) ||
                        it.phone.contains(searchQuery, ignoreCase = true)
                    }

                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        items(filteredCustomers, key = { it.id }) { customer ->
                            CustomerCard(
                                customer = customer,
                                onEdit = {
                                    selectedCustomer = customer
                                    showDialog = true
                                },
                                onDelete = {
                                    customerToDelete = customer
                                    showDeleteDialog = true
                                },
                                onClick = {
                                    navController.navigate("customer_detail/${customer.id}")
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    // Add/Edit Dialog
    if (showDialog) {
        CustomerFormDialog(
            customer = selectedCustomer,
            onDismiss = { showDialog = false },
            onSave = { customer ->
                if (selectedCustomer == null) {
                    viewModel.createCustomer(customer)
                } else {
                    viewModel.updateCustomer(customer)
                }
                showDialog = false
            }
        )
    }

    // Delete Confirmation Dialog
    if (showDeleteDialog && customerToDelete != null) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Delete Customer", color = White) },
            text = {
                Text(
                    "Are you sure you want to delete ${customerToDelete?.name}?",
                    color = LightGrey
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        customerToDelete?.let { viewModel.deleteCustomer(it.id) }
                        showDeleteDialog = false
                        customerToDelete = null
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ErrorRed,
                        contentColor = White
                    )
                ) {
                    Text("Delete")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDeleteDialog = false
                        customerToDelete = null
                    },
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = LightGrey
                    )
                ) {
                    Text("Cancel")
                }
            },
            containerColor = CardBackground
        )
    }
}

@Composable
fun CustomerCard(
    customer: Customer,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = CardBackground
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = customer.name,
                    style = MaterialTheme.typography.titleLarge,
                    color = White,
                    fontWeight = FontWeight.Bold
                )
                
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        Icons.Filled.Email,
                        contentDescription = null,
                        tint = PastelBlue,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = customer.email,
                        style = MaterialTheme.typography.bodyMedium,
                        color = LightGrey
                    )
                }
                
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        Icons.Filled.Phone,
                        contentDescription = null,
                        tint = PastelBlue,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = customer.phone,
                        style = MaterialTheme.typography.bodyMedium,
                        color = LightGrey
                    )
                }
            }

            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                IconButton(
                    onClick = onEdit,
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = PastelBlue
                    )
                ) {
                    Icon(
                        Icons.Filled.Edit,
                        contentDescription = "Edit",
                        modifier = Modifier.size(24.dp)
                    )
                }
                
                IconButton(
                    onClick = onDelete,
                    colors = IconButtonDefaults.iconButtonColors(
                        contentColor = ErrorRed
                    )
                ) {
                    Icon(
                        Icons.Filled.Delete,
                        contentDescription = "Delete",
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}
