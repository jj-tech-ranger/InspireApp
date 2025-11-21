package com.inspirewear.android.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.inspirewear.android.ui.customer.CustomerDetailScreen
import com.inspirewear.android.ui.customer.CustomerListScreen
import com.inspirewear.android.ui.dashboard.DashboardScreen
import com.inspirewear.android.ui.finance.FinanceScreen
import com.inspirewear.android.ui.inventory.InventoryListScreen
import com.inspirewear.android.ui.loyalty.LoyaltyScreen
import com.inspirewear.android.ui.order.OrderDetailScreen
import com.inspirewear.android.ui.order.OrderListScreen
import com.inspirewear.android.ui.product.ProductDetailScreen
import com.inspirewear.android.ui.product.ProductListScreen
import com.inspirewear.android.ui.reports.ReportsScreen
import com.inspirewear.android.ui.supplier.SupplierListScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = NavigationItem.Dashboard.route) {
        composable(NavigationItem.Dashboard.route) {
            DashboardScreen(navController)
        }
        composable(NavigationItem.Products.route) {
            ProductListScreen(navController = navController)
        }
        composable(
            route = "productDetail/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.LongType })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments?.getLong("productId")
            if (productId != null) {
                ProductDetailScreen(productId = productId)
            }
        }
        composable(NavigationItem.Orders.route) {
            OrderListScreen(navController = navController)
        }
        composable(
            route = "orderDetail/{orderId}",
            arguments = listOf(navArgument("orderId") { type = NavType.LongType })
        ) { backStackEntry ->
            val orderId = backStackEntry.arguments?.getLong("orderId")
            if (orderId != null) {
                OrderDetailScreen(orderId = orderId)
            }
        }
        composable(NavigationItem.Customers.route) {
            CustomerListScreen(navController = navController)
        }
        composable(
            route = "customerDetail/{customerId}",
            arguments = listOf(navArgument("customerId") { type = NavType.LongType })
        ) { backStackEntry ->
            val customerId = backStackEntry.arguments?.getLong("customerId")
            if (customerId != null) {
                CustomerDetailScreen(customerId = customerId)
            }
        }
        composable(NavigationItem.Inventory.route) {
            InventoryListScreen()
        }
        composable(NavigationItem.Reports.route) {
            ReportsScreen()
        }
        composable(NavigationItem.Suppliers.route) {
            SupplierListScreen()
        }
        composable(NavigationItem.Finance.route) {
            FinanceScreen()
        }
        composable(NavigationItem.Loyalty.route) {
            LoyaltyScreen()
        }
    }
}