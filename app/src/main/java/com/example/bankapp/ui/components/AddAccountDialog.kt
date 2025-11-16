package com.example.bankapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bankapp.data.Account
import com.example.bankapp.data.Account.AccountType
import java.math.BigDecimal

@Composable
fun AddAccountDialog(
    onDismiss: () -> Unit,
    onAdd: (Account) -> Unit
) {
    var solde by remember { mutableStateOf("") }
    var selectedType by remember { mutableStateOf(AccountType.COURANT) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nouveau compte") },
        text = {
            Column {
                OutlinedTextField(
                    value = solde,
                    onValueChange = { solde = it },
                    label = { Text("Solde") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    RadioButton(
                        selected = selectedType == AccountType.COURANT,
                        onClick = { selectedType = AccountType.COURANT }
                    )
                    Text("COURANT", modifier = Modifier.padding(start = 8.dp))
                    Spacer(modifier = Modifier.width(16.dp))
                    RadioButton(
                        selected = selectedType == AccountType.EPARGNE,
                        onClick = { selectedType = AccountType.EPARGNE }
                    )
                    Text("EPARGNE", modifier = Modifier.padding(start = 8.dp))
                }
            }
        },
        confirmButton = {
            TextButton(onClick = {
                val balance = try { BigDecimal(solde) } catch (e: NumberFormatException) { BigDecimal.ZERO }
                val newAccount = Account(
                    name = "Compte N°${System.currentTimeMillis() % 1000}",
                    balance = balance,
                    type = selectedType
                )
                onAdd(newAccount)
                onDismiss()
            }) {
                Text("Ajouter")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("Annuler")
            }
        }
    )
}