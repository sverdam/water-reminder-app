package com.sofiaverdugo.water_reminder_app.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.sofiaverdugo.water_reminder_app.R
import com.sofiaverdugo.water_reminder_app.models.Register

@Composable
fun RegisterCard(
    register: Register,
    onReply: (Boolean) -> Unit
)
{
    val color = when (register.drankWater) {
    true -> MaterialTheme.colorScheme.primaryContainer
    false -> MaterialTheme.colorScheme.errorContainer
    null -> MaterialTheme.colorScheme.surfaceVariant
}
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = color)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = stringResource(R.string.reminder_received),
                fontWeight = FontWeight.Bold
            )
            Text(text = "${register.day()} ${register.hour()}")

            if (register.drankWater == null) {
                Row(
                    modifier = Modifier.padding(top = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Button(onClick = { onReply(true) }) {
                        Text(stringResource(R.string.drink_water))
                    }
                    OutlinedButton(onClick = { onReply(false) }) {
                        Text(stringResource(R.string.not_now))
                    }
                }

            }
        }
    }
}