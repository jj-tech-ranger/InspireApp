package com.inspirewear.android.ui.supplier

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
import com.inspirewear.android.data.supplier.Supplier
import java.util.Date

@Composable
fun SupplierFormDialog(
    supplier: Supplier?,
    onDismiss: () -> Unit,
    onSave: (Supplier) -> Unit
) {
    val isEditing = supplier != null
    var name by remember { mutableStateOf(supplier?.name ?: "") }
    var code by remember { mutableStateOf(supplier?.code ?: "") }
    var contactPerson by remember { mutableStateOf(supplier?.contact_person ?: "") }
    var category by remember { mutableStateOf(supplier?.category ?: "") }
    var phone by remember { mutableStateOf(supplier?.phone ?: "") }
    var email by remember { mutableStateOf(supplier?.email ?: "") }
    var address by remember { mutableStateOf(supplier?.address ?: "") }
    var location by remember { mutableStateOf(supplier?.location ?: "") }
    var paymentTerms by remember { mutableStateOf(supplier?.payment_terms?.toString() ?: "") }
    var status by remember { mutableStateOf(supplier?.status ?: "") }

    Dialog(onDismissRequest = onDismiss) {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(if (isEditing) "Edit Supplier" else "Add Supplier")
                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
                OutlinedTextField(value = code, onValueChange = { code = it }, label = { Text("Code") })
                OutlinedTextField(value = contactPerson, onValueChange = { contactPerson = it }, label = { Text("Contact Person") })
                OutlinedTextField(value = category, onValueChange = { category = it }, label = { Text("Category") })
                OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("Phone") })
                OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") })
                OutlinedTextField(value = address, onValueChange = { address = it }, label = { Text("Address") })
                OutlinedTextField(value = location, onValueChange = { location = it }, label = { Text("Location") })
                OutlinedTextField(value = paymentTerms, onValueChange = { paymentTerms = it }, label = { Text("Payment Terms") })
                OutlinedTextField(value = status, onValueChange = { status = it }, label = { Text("Status") })
                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    Button(onClick = {
                        val newSupplier = Supplier(
                            id = supplier?.id ?: 0,
                            name = name,
                            code = code,
                            contact_person = contactPerson,
                            category = category,
                            phone = phone,
                            email = email,
                            address = address,
                            location = location,
                            payment_terms = paymentTerms.toIntOrNull() ?: 0,
                            status = status,
                            notes = supplier?.notes,
                            join_date = supplier?.join_date ?: Date(),
                            last_order = supplier?.last_order,
                            total_orders = supplier?.total_orders ?: 0,
                            rating = supplier?.rating
                        )
                        onSave(newSupplier)
                    }) {
                        Text("Save")
                    }
                }
            }
        }
    }
}