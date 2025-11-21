package com.inspirewear.android.data.loyalty

import com.inspirewear.android.data.customer.Customer
import java.util.Date

data class LoyaltyMember(
    val id: Long,
    val customer: Customer,
    val points: Int,
    val tier: String,
    val last_activity: Date
)

data class Reward(
    val id: Long,
    val name: String,
    val description: String,
    val points: Int,
    val category: String,
    val stock: Int,
    val image: String?
)