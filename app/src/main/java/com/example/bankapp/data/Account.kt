package com.example.bankapp.data

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.SimpleDateFormat
import java.util.*

data class Account(
    val id: Long = System.currentTimeMillis(),
    val name: String,
    val balance: BigDecimal,
    val type: AccountType,
    val createdAt: Date = Date()
) {
    enum class AccountType { COURANT, EPARGNE }

    fun formattedBalance(): String = balance.setScale(2, RoundingMode.HALF_EVEN).toString() + " DH"

    fun formattedDate(): String = SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH).format(createdAt)

    fun typeLabel(): String = when (type) {
        AccountType.COURANT -> "COURANT"
        AccountType.EPARGNE -> "EPARGNE"
    }

    fun typeColor(): Int = when (type) {
        AccountType.COURANT -> 0xFFE67E22.toInt() // orange
        AccountType.EPARGNE -> 0xFF27AE60.toInt() // vert
    }
}