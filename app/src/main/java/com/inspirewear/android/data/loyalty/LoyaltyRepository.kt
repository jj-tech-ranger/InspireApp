package com.inspirewear.android.data.loyalty

import com.inspirewear.android.network.RetrofitClient

class LoyaltyRepository {
    suspend fun getLoyaltyData(): LoyaltyResponse {
        return RetrofitClient.apiService.getLoyaltyData()
    }
}