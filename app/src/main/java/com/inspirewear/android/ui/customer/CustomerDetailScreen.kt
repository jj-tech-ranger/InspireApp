package com.inspirewear.android.ui.customer

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun CustomerDetailScreen(viewModel: CustomerViewModel = viewModel(), customerId: Long) {
    viewModel.getCustomerById(customerId)
    val customer by viewModel.selectedCustomer.collectAsState()

    customer?.let {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "${it.first_name} ${it.last_name}", style = MaterialTheme.typography.headlineSmall)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Email: ${it.email}", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Phone: ${it.phone}", style = MaterialTheme.typography.bodyLarge)
                    Text(text = "Location: ${it.location}", style = MaterialTheme.typography.bodyLarge)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Total Spent", style = MaterialTheme.typography.titleMedium)
                    Text(text = "$${it.total_spent}", style = MaterialTheme.typography.bodyLarge)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "Status", style = MaterialTheme.typography.titleMedium)
                    Text(text = it.status, style = MaterialTheme.typography.bodyLarge)
                }
            }
        }
    }
}