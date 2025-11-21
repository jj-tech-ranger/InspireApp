package com.inspirewear.android.data.supplier

import java.util.Date

data class Supplier(
    val id: Long,
    val name: String,
    val code: String,
    val contact_person: String,
    val category: String,
    val phone: String,
    val email: String,
    val address: String,
    val location: String,
    val payment_terms: Int,
    val status: String,
    val notes: String?,
    val join_date: Date,
    val last_order: Date?,
    val total_orders: Int,
    val rating: Float?
)