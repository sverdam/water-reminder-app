package com.sofiaverdugo.water_reminder_app.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.sofiaverdugo.water_reminder_app.R
import com.sofiaverdugo.water_reminder_app.viewmodels.WaterViewModel

@Composable
fun MainScreen(
    viewModel: WaterViewModel,
    onButton: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = stringResource(R.string.title),
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onButton, modifier = Modifier.fillMaxWidth()) {
            Text(stringResource(R.string.btn_send))
        }
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.history),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(8.dp))
        if (viewModel.registers.isEmpty()) {
            Text(text = stringResource(R.string.no_registers))
        } else {
            LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(viewModel.registers, key = { it.id }) { register ->
                    RegisterCard(
                        register = register,
                        onReply = { drank -> viewModel.reply(register.id, drank) }
                    )
                }
            }
        }
    }
}
