package com.inspirewear.android.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.AttachMoney
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.Inventory
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.SupervisedUserCircle
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationItem(val route: String, val icon: ImageVector, val title: String) {
    object Dashboard : NavigationItem("dashboard", Icons.Default.Dashboard, "Dashboard")
    object Products : NavigationItem("products", Icons.Default.ShoppingCart, "Products")
    object Orders : NavigationItem("orders", Icons.Default.List, "Orders")
    object Customers : NavigationItem("customers", Icons.Default.Person, "Customers")
    object Suppliers : NavigationItem("suppliers", Icons.Default.SupervisedUserCircle, "Suppliers")
    object Inventory : NavigationItem("inventory", Icons.Default.Inventory, "Inventory")
    object Reports : NavigationItem("reports", Icons.Default.Assessment, "Reports")
    object Finance : NavigationItem("finance", Icons.Default.AttachMoney, "Finance")
    object Loyalty : NavigationItem("loyalty", Icons.Default.Star, "Loyalty")
}