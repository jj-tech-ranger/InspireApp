package com.inspirewear.android.ui.finance

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
import com.inspirewear.android.data.finance.Transaction

@Composable
fun FinanceScreen(viewModel: FinanceViewModel = viewModel()) {
    val transactions by viewModel.transactions.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var selectedTransaction by remember { mutableStateOf<Transaction?>(null) }

    viewModel.getTransactions()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true; selectedTransaction = null }) {
                Icon(Icons.Filled.Add, contentDescription = "Add Transaction")
            }
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues), contentPadding = PaddingValues(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            items(transactions) { transaction ->
                TransactionItem(
                    transaction = transaction,
                    onEditClick = { showDialog = true; selectedTransaction = transaction },
                    onDeleteClick = { viewModel.deleteTransaction(transaction.id) },
                    onItemClick = {}
                )
            }
        }
    }

    if (showDialog) {
        TransactionFormDialog(
            transaction = selectedTransaction,
            onDismiss = { showDialog = false },
            onSave = {
                if (selectedTransaction == null) {
                    viewModel.addTransaction(it)
                } else {
                    viewModel.updateTransaction(it.id, it)
                }
                showDialog = false
            }
        )
    }
}

@Composable
fun TransactionItem(transaction: Transaction, onEditClick: () -> Unit, onDeleteClick: () -> Unit, onItemClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onItemClick),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = transaction.description, style = MaterialTheme.typography.titleMedium)
                Text(text = "Amount: ${transaction.amount}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Date: ${transaction.date}", style = MaterialTheme.typography.bodyMedium)
                Text(text = "Status: ${transaction.status}", style = MaterialTheme.typography.bodyMedium)
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