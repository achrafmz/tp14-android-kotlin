package com.example.bankapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bankapp.data.Account
import com.example.bankapp.ui.components.*
import com.example.bankapp.viewmodel.AccountViewModel

@Composable
fun AccountListScreen(viewModel: AccountViewModel = viewModel()) {
    val accounts by viewModel.accounts.collectAsState()

    var showAddDialog by remember { mutableStateOf(false) }
    var showConfirmDeleteDialog by remember { mutableStateOf(false) }
    var accountToDelete by remember { mutableStateOf<Account?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Gestion des Comptes") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddDialog = true },
                backgroundColor = MaterialTheme.colors.primary
            ) {
                Text("Ajouter", color = Color.White, fontSize = 14.sp)
            }
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (accounts.isEmpty()) {
                    Text("Aucun compte", modifier = Modifier.align(Alignment.CenterHorizontally))
                } else {
                    accounts.forEach { account ->
                        AccountCard(
                            account = account,
                            onModify = { },
                            onDelete = {
                                accountToDelete = account
                                showConfirmDeleteDialog = true
                            },
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
    )

    if (showAddDialog) {
        AddAccountDialog(
            onDismiss = { showAddDialog = false },
            onAdd = { account -> viewModel.addAccount(account) }
        )
    }

    if (showConfirmDeleteDialog && accountToDelete != null) {
        ConfirmDeleteDialog(
            onDismiss = { showConfirmDeleteDialog = false },
            onConfirm = {
                accountToDelete?.let { acc -> viewModel.deleteAccount(acc.id) }
                accountToDelete = null
            }
        )
    }
}