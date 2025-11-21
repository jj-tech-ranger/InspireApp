package com.inspirewear.android.ui.customer

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
import com.inspirewear.android.data.customer.Customer
import java.util.Date

@Composable
fun CustomerFormDialog(
    customer: Customer?,
    onDismiss: () -> Unit,
    onSave: (Customer) -> Unit
) {
    val isEditing = customer != null
    var firstName by remember { mutableStateOf(customer?.first_name ?: "") }
    var lastName by remember { mutableStateOf(customer?.last_name ?: "") }
    var email by remember { mutableStateOf(customer?.email ?: "") }
    var phone by remember { mutableStateOf(customer?.phone ?: "") }
    var location by remember { mutableStateOf(customer?.location ?: "") }

    Dialog(onDismissRequest = onDismiss) {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(if (isEditing) "Edit Customer" else "Add Customer")
                OutlinedTextField(value = firstName, onValueChange = { firstName = it }, label = { Text("First Name") })
                OutlinedTextField(value = lastName, onValueChange = { lastName = it }, label = { Text("Last Name") })
                OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
                OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("Phone") })
                OutlinedTextField(value = location, onValueChange = { location = it }, label = { Text("Location") })
                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    Button(onClick = {
                        val newCustomer = Customer(
                            id = customer?.id ?: 0,
                            first_name = firstName,
                            last_name = lastName,
                            email = email,
                            phone = phone,
                            location = location,
                            total_spent = customer?.total_spent ?: 0.0,
                            last_purchase = customer?.last_purchase,
                            status = customer?.status ?: "active"
                        )
                        onSave(newCustomer)
                    }) {
                        Text("Save")
                    }
                }
            }
        }
    }
}