package com.inspirewear.android.ui.finance

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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.inspirewear.android.data.finance.Transaction
import java.util.Date

@Composable
fun TransactionFormDialog(
    transaction: Transaction?,
    onDismiss: () -> Unit,
    onSave: (Transaction) -> Unit
) {
    val isEditing = transaction != null
    var description by rememberSaveable { mutableStateOf(transaction?.description ?: "") }
    var location by rememberSaveable { mutableStateOf(transaction?.location ?: "") }
    var amount by rememberSaveable { mutableStateOf(transaction?.amount?.toString() ?: "") }
    var type by rememberSaveable { mutableStateOf(transaction?.type ?: "") }
    var status by rememberSaveable { mutableStateOf(transaction?.status ?: "") }

    Dialog(onDismissRequest = onDismiss) {
        Surface {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(if (isEditing) "Edit Transaction" else "Add Transaction")
                OutlinedTextField(value = description, onValueChange = { description = it }, label = { Text("Description") })
                OutlinedTextField(value = location, onValueChange = { location = it }, label = { Text("Location") })
                OutlinedTextField(value = amount, onValueChange = { amount = it }, label = { Text("Amount") })
                OutlinedTextField(value = type, onValueChange = { type = it }, label = { Text("Type") })
                OutlinedTextField(value = status, onValueChange = { status = it }, label = { Text("Status") })
                Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                    Button(onClick = onDismiss) {
                        Text("Cancel")
                    }
                    Button(onClick = {
                        val newTransaction = Transaction(
                            id = transaction?.id ?: 0,
                            date = transaction?.date ?: Date(),
                            description = description,
                            location = location,
                            amount = amount.toDoubleOrNull() ?: 0.0,
                            type = type,
                            status = status
                        )
                        onSave(newTransaction)
                    }) {
                        Text("Save")
                    }
                }
            }
        }
    }
}