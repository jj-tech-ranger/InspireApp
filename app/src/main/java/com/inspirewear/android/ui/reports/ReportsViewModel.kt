package com.inspirewear.android.ui.reports

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inspirewear.android.data.reports.CustomerReport
import com.inspirewear.android.data.reports.ReportsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ReportsViewModel : ViewModel() {

    private val repository = ReportsRepository()

    private val _customerReport = MutableStateFlow<CustomerReport?>(null)
    val customerReport: StateFlow<CustomerReport?> = _customerReport

    fun getCustomerReport() {
        viewModelScope.launch {
            _customerReport.value = repository.getCustomerReport()
        }
    }
}