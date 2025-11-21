package com.inspirewear.android.ui.reports

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.inspirewear.android.data.reports.CustomerReport
import com.inspirewear.android.data.reports.Metric
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun ReportsScreen(viewModel: ReportsViewModel = viewModel()) {
    val customerReport by viewModel.customerReport.collectAsState()

    viewModel.getCustomerReport()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        customerReport?.let {
            Text("Customer Report")
            it.metrics.forEach { metric ->
                MetricItem(metric = metric)
            }
            BarChart(data = it.customer_growth)
        }
    }
}

@Composable
fun MetricItem(metric: Metric) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = metric.metric)
        Text(text = "Current: ${metric.current}")
        Text(text = "Previous: ${metric.previous}")
        Text(text = "Change: ${metric.change}")
    }
}

@Composable
fun BarChart(data: com.inspirewear.android.data.reports.CustomerGrowth) {
    val entries = data.new_customers.mapIndexed { index, value ->
        BarEntry(index.toFloat(), value.toFloat())
    }
    val dataSet = BarDataSet(entries, "New Customers")
    val barData = BarData(dataSet)

    AndroidView(factory = {
        context -> BarChart(context).apply { this.data = barData }
    })
}