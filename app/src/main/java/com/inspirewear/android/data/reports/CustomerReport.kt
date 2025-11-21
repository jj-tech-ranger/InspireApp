package com.inspirewear.android.data.reports

data class CustomerReport(
    val metrics: List<Metric>,
    val customer_growth: CustomerGrowth,
    val customer_locations: ChartData,
    val loyalty_tiers: ChartData,
    val feedback_sentiment: ChartData,
    val campaign_performance: ChartData
)

data class Metric(
    val metric: String,
    val current: String,
    val previous: String,
    val change: String,
    val trend: String
)

data class CustomerGrowth(
    val labels: List<String>,
    val new_customers: List<Int>,
    val returning_customers: List<Int>
)

data class ChartData(
    val labels: List<String>,
    val data: List<Int>,
    val colors: List<String>
)