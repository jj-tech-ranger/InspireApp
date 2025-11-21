package com.inspirewear.android.ui.inventory

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
import com.inspirewear.android.data.inventory.InventoryItem

@Composable
fun InventoryFormDialog(
    item: InventoryItem?,
    onDismiss: () -> Unit,
    onSave: (InventoryItem) -> Unit
) {
    val isEditing = item != null
    var name by remember { mutableStateOf(item?.name ?: "") }
    var category by remember { mutableStateOf(item?.category ?: "") }
    var size by remember { mutableStateOf(item?.size ?: "") }
    var color by remember { mutableStateOf(item?.color ?: "") }
    var stock by remember { mutableStateOf(item?.stock?.toString() ?: "") }
    var reorderLevel by remember { mutableStateOf(item?.reorder_level?.toString() ?: "") }
    var value by remember { mutableStateOf(item?.value?.toString() ?: "") }

    Dialog(onDismissRequest = onDismiss) {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(if (isEditing) "Edit Item" else "Add Item")
                OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") })
                OutlinedTextField(value = category, onValueChange = { category = it }, label = { Text("Category") })
                OutlinedTextField(value = size, onValueChange = { size = it }, label = { Text("Size") })
                OutlinedTextField(value = color, onValueChange = { color = it }, label = { Text("Color") })
                OutlinedTextField(value = stock, onValueChange = { stock = it }, label = { Text("Stock") })
                OutlinedTextField(value = reorderLevel, onValueChange = { reorderLevel = it }, label = { Text("Reorder Level") })
                OutlinedTextField(value = value, onValueChange = { value = it }, label = { Text("Value") })
                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    Button(onClick = {
                        val newItem = InventoryItem(
                            id = item?.id ?: 0,
                            name = name,
                            category = category,
                            size = size,
                            color = color,
                            stock = stock.toIntOrNull() ?: 0,
                            reorder_level = reorderLevel.toIntOrNull() ?: 0,
                            value = value.toDoubleOrNull() ?: 0.0
                        )
                        onSave(newItem)
                    }) {
                        Text("Save")
                    }
                }
            }
        }
    }
}