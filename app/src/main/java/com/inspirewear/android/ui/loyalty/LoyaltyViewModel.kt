package com.inspirewear.android.ui.loyalty

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inspirewear.android.data.loyalty.LoyaltyMember
import com.inspirewear.android.data.loyalty.LoyaltyRepository
import com.inspirewear.android.data.loyalty.Reward
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoyaltyViewModel : ViewModel() {

    private val repository = LoyaltyRepository()

    private val _loyaltyMembers = MutableStateFlow<List<LoyaltyMember>>(emptyList())
    val loyaltyMembers: StateFlow<List<LoyaltyMember>> = _loyaltyMembers

    private val _rewards = MutableStateFlow<List<Reward>>(emptyList())
    val rewards: StateFlow<List<Reward>> = _rewards

    fun getLoyaltyData() {
        viewModelScope.launch {
            val loyaltyData = repository.getLoyaltyData()
            _loyaltyMembers.value = loyaltyData.loyaltyMembers
            _rewards.value = loyaltyData.rewards
        }
    }
}