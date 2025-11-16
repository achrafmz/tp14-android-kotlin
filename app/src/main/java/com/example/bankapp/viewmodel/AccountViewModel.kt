package com.example.bankapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bankapp.data.Account
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.math.BigDecimal

class AccountViewModel : ViewModel() {

    private val _accounts = MutableStateFlow<List<Account>>(emptyList())
    val accounts: StateFlow<List<Account>> = _accounts

    init {
        val sampleAccounts = listOf(
            Account(name = "Compte N°1", balance = BigDecimal("2000.0"), type = Account.AccountType.COURANT),
            Account(name = "Compte N°2", balance = BigDecimal("30007.0"), type = Account.AccountType.COURANT),
            Account(name = "Compte N°3", balance = BigDecimal("45999.688"), type = Account.AccountType.EPARGNE)
        )
        _accounts.value = sampleAccounts
    }

    fun addAccount(account: Account) {
        viewModelScope.launch {
            val updated = _accounts.value + account
            _accounts.value = updated
        }
    }

    fun deleteAccount(id: Long) {
        viewModelScope.launch {
            val updated = _accounts.value.filter { it.id != id }
            _accounts.value = updated
        }
    }
}