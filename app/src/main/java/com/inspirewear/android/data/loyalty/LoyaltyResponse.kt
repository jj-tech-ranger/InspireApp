package com.inspirewear.android.data.loyalty

data class LoyaltyResponse(
    val loyaltyMembers: List<LoyaltyMember>,
    val rewards: List<Reward>
)