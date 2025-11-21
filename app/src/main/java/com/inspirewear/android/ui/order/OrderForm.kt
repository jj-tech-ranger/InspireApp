package com.inspirewear.android.ui.order

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.inspirewear.android.data.order.Order

@Composable
fun OrderFormDialog(
    order: Order?,
    onDismiss: () -> Unit,
    onSave: (Order) -> Unit
) {
    val isEditing = order != null
    var customerName by remember { mutableStateOf(order?.customerName ?: "") }
    var total by remember { mutableStateOf(order?.total?.toString() ?: "") }

    Dialog(onDismissRequest = onDismiss) {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(if (isEditing) "Edit Order" else "Add Order")
                OutlinedTextField(value = customerName, onValueChange = { customerName = it }, label = { Text("Customer Name") })
                OutlinedTextField(value = total, onValueChange = { total = it }, label = { Text("Total") })
                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    Button(onClick = {
                        val newOrder = Order(
                            id = order?.id ?: 0,
                            customerName = customerName,
                            total = total.toDoubleOrNull() ?: 0.0,
                            products = order?.products ?: emptyList()
                        )
                        onSave(newOrder)
                    }) {
                        Text("Save")
                    }
                }
            }
        }
    }
}