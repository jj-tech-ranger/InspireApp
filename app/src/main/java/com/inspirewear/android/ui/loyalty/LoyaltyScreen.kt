package com.inspirewear.android.ui.loyalty

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.inspirewear.android.data.loyalty.LoyaltyMember
import com.inspirewear.android.data.loyalty.Reward

@Composable
fun LoyaltyScreen(viewModel: LoyaltyViewModel = viewModel()) {
    val loyaltyMembers by viewModel.loyaltyMembers.collectAsState()
    val rewards by viewModel.rewards.collectAsState()

    viewModel.getLoyaltyData()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Loyalty Members", style = MaterialTheme.typography.headlineSmall)
        LazyColumn(contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp)) {
            items(loyaltyMembers) { member ->
                LoyaltyMemberItem(member = member)
            }
        }
        Text(text = "Rewards", style = MaterialTheme.typography.headlineSmall)
        LazyColumn(contentPadding = PaddingValues(top = 8.dp, bottom = 8.dp)) {
            items(rewards) { reward ->
                RewardItem(reward = reward)
            }
        }
    }
}

@Composable
fun LoyaltyMemberItem(member: LoyaltyMember) {
    Card(
        modifier = Modifier.fillMaxSize().padding(bottom = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "${member.customer.first_name} ${member.customer.last_name}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Points: ${member.points}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Tier: ${member.tier}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun RewardItem(reward: Reward) {
    Card(
        modifier = Modifier.fillMaxSize().padding(bottom = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = reward.name, style = MaterialTheme.typography.titleMedium)
            Text(text = reward.description, style = MaterialTheme.typography.bodyMedium)
            Text(text = "Points: ${reward.points}", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
