package com.inspirewear.android.ui.product

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
import com.inspirewear.android.data.models.Product

@Composable
fun ProductFormDialog(
    product: Product?,
    onDismiss: () -> Unit,
    onSave: (Product) -> Unit
) {
    val isEditing = product != null
    var name by remember { mutableStateOf(product?.name ?: "") }
    var description by remember { mutableStateOf(product?.description ?: "") }
    var price by remember { mutableStateOf(product?.price?.toString() ?: "") }

    Dialog(onDismissRequest = onDismiss) {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(if (isEditing) "Edit Product" else "Add Product")
                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
                OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Description") })
                OutlinedTextField(value = price, onValueChange = { price = it }, label = { Text("Price") })
                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    Button(onClick = {
                        val newProduct = Product(
                            id = product?.id ?: 0,
                            name = name,
                            description = description,
                            price = price.toDoubleOrNull() ?: 0.0
                        )
                        onSave(newProduct)
                    }) {
                        Text("Save")
                    }
                }
            }
        }
    }
}