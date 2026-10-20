package com.sofiaverdugo.water_reminder_app

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContextCompat
import com.sofiaverdugo.water_reminder_app.notifications.Notifier
import com.sofiaverdugo.water_reminder_app.ui.theme.WaterreminderappTheme
import com.sofiaverdugo.water_reminder_app.viewmodels.WaterViewModel
import com.sofiaverdugo.water_reminder_app.views.MainScreen
class MainActivity : ComponentActivity() {
    private val viewModel: WaterViewModel by viewModels()
    private lateinit var notifier: Notifier

    private val askForPermission = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            sendReminder()
        } else {
            Toast.makeText(this, R.string.permission_necessary, Toast.LENGTH_LONG).show()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        notifier = Notifier(this)
        notifier.createChannel()
        enableEdgeToEdge()
        setContent {
            WaterreminderappTheme() {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        viewModel = viewModel,
                        onButton = { checkPermissionAndSend() },
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    private fun hasPermission(): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            return true
        }
        return ContextCompat.checkSelfPermission(
            this, Manifest.permission.POST_NOTIFICATIONS
        ) == PackageManager.PERMISSION_GRANTED
    }
    private fun checkPermissionAndSend() {
        if (hasPermission()) {
            sendReminder()
        } else {
            askForPermission.launch(Manifest.permission.POST_NOTIFICATIONS)
        }
    }

    private fun sendReminder() {
        notifier.show()
        viewModel.addRegister()
    }
}
    @Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    WaterreminderappTheme {
        Greeting("Android")
    }
}