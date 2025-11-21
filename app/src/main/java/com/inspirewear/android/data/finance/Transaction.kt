package com.inspirewear.android.data.finance

import java.util.Date

data class Transaction(
    val id: Long,
    val date: Date,
    val description: String,
    val location: String,
    val amount: Double,
    val type: String,
    val status: String
)