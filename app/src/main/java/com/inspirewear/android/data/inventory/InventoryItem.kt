package com.inspirewear.android.data.inventory

data class InventoryItem(
    val id: Long,
    val name: String,
    val category: String,
    val size: String,
    val color: String,
    val stock: Int,
    val reorder_level: Int,
    val value: Double
)