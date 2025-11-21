package com.inspirewear.android.ui.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.inspirewear.android.ui.navigation.NavigationItem

data class DashboardItem(
    val title: String,
    val description: String,
    val route: String,
    val icon: ImageVector
)

@Composable
fun DashboardScreen(navController: NavController) {
    val dashboardItems = listOf(
        DashboardItem("Products", "Manage your product catalog", NavigationItem.Products.route, NavigationItem.Products.icon),
        DashboardItem("Orders", "View and process customer orders", NavigationItem.Orders.route, NavigationItem.Orders.icon),
        DashboardItem("Customers", "Manage your customer base", NavigationItem.Customers.route, NavigationItem.Customers.icon),
        DashboardItem("Suppliers", "Manage your suppliers", NavigationItem.Suppliers.route, NavigationItem.Suppliers.icon),
        DashboardItem("Inventory", "Track your inventory levels", NavigationItem.Inventory.route, NavigationItem.Inventory.icon),
        DashboardItem("Reports", "View sales and customer reports", NavigationItem.Reports.route, NavigationItem.Reports.icon),
        DashboardItem("Finance", "Track your income and expenses", NavigationItem.Finance.route, NavigationItem.Finance.icon)
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(dashboardItems) { item ->
            DashboardCard(item = item, onClick = { navController.navigate(item.route) })
        }
    }
}

@Composable
fun DashboardCard(item: DashboardItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(item.icon, contentDescription = item.title, modifier = Modifier.size(48.dp))
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = item.title, style = MaterialTheme.typography.titleMedium)
            Text(text = item.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}