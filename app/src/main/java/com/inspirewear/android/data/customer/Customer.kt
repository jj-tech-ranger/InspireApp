package com.inspirewear.android.data.customer

import java.util.Date

data class Customer(
    val id: Long,
    val first_name: String,
    val last_name: String,
    val email: String,
    val phone: String,
    val location: String,
    val total_spent: Double,
    val last_purchase: Date?,
    val status: String
)