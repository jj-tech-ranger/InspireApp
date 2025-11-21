package com.inspirewear.android.network

import com.inspirewear.android.data.customer.Customer
import com.inspirewear.android.data.customer.CustomersResponse
import com.inspirewear.android.data.finance.Transaction
import com.inspirewear.android.data.finance.TransactionsResponse
import com.inspirewear.android.data.inventory.InventoryItem
import com.inspirewear.android.data.inventory.InventoryResponse
import com.inspirewear.android.data.loyalty.LoyaltyResponse
import com.inspirewear.android.data.models.Product
import com.inspirewear.android.data.models.ProductsResponse
import com.inspirewear.android.data.order.Order
import com.inspirewear.android.data.order.OrdersResponse
import com.inspirewear.android.data.reports.CustomerReport
import com.inspirewear.android.data.supplier.Supplier
import com.inspirewear.android.data.supplier.SuppliersResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @GET("products")
    suspend fun getProducts(): ProductsResponse

    @POST("products")
    suspend fun addProduct(@Body product: Product): Product

    @PUT("products/{id}")
    suspend fun updateProduct(@Path("id") id: Long, @Body product: Product): Product

    @DELETE("products/{id}")
    suspend fun deleteProduct(@Path("id") id: Long): Response<Unit>

    @GET("orders")
    suspend fun getOrders(): OrdersResponse

    @POST("orders")
    suspend fun addOrder(@Body order: Order): Order

    @PUT("orders/{id}")
    suspend fun updateOrder(@Path("id") id: Long, @Body order: Order): Order

    @DELETE("orders/{id}")
    suspend fun deleteOrder(@Path("id") id: Long): Response<Unit>

    @GET("customers")
    suspend fun getCustomers(): CustomersResponse

    @POST("customers")
    suspend fun addCustomer(@Body customer: Customer): Customer

    @PUT("customers/{id}")
    suspend fun updateCustomer(@Path("id") id: Long, @Body customer: Customer): Customer

    @DELETE("customers/{id}")
    suspend fun deleteCustomer(@Path("id") id: Long): Response<Unit>

    @GET("inventory")
    suspend fun getInventory(): InventoryResponse

    @POST("inventory")
    suspend fun addInventoryItem(@Body item: InventoryItem): InventoryItem

    @PUT("inventory/{id}")
    suspend fun updateInventoryItem(@Path("id") id: Long, @Body item: InventoryItem): InventoryItem

    @DELETE("inventory/{id}")
    suspend fun deleteInventoryItem(@Path("id") id: Long): Response<Unit>

    @GET("reports/customers")
    suspend fun getCustomerReport(): CustomerReport

    @GET("suppliers")
    suspend fun getSuppliers(): SuppliersResponse

    @POST("suppliers")
    suspend fun addSupplier(@Body supplier: Supplier): Supplier

    @PUT("suppliers/{id}")
    suspend fun updateSupplier(@Path("id") id: Long, @Body supplier: Supplier): Supplier

    @DELETE("suppliers/{id}")
    suspend fun deleteSupplier(@Path("id") id: Long): Response<Unit>

    @GET("finance/transactions")
    suspend fun getTransactions(): TransactionsResponse

    @POST("finance/transactions")
    suspend fun addTransaction(@Body transaction: Transaction): Transaction

    @PUT("finance/transactions/{id}")
    suspend fun updateTransaction(@Path("id") id: Long, @Body transaction: Transaction): Transaction

    @DELETE("finance/transactions/{id}")
    suspend fun deleteTransaction(@Path("id") id: Long): Response<Unit>

    @GET("loyalty")
    suspend fun getLoyaltyData(): LoyaltyResponse
}