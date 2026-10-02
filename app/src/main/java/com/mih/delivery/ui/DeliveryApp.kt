package com.mih.delivery.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

enum class Screen { Welcome, Book, Confirmation }

@Composable
fun DeliveryApp() {
    var screen by rememberSaveable { mutableStateOf(Screen.Welcome) }
    Surface(modifier = Modifier.fillMaxSize()) {
        when (screen) {
            Screen.Welcome -> WelcomeScreen(onBook = { screen = Screen.Book })
            Screen.Book -> BookingScreen(onBack = { screen = Screen.Welcome }, onConfirm = { screen = Screen.Confirmation })
            Screen.Confirmation -> ConfirmationScreen(onNewBooking = { screen = Screen.Book })
        }
    }
}

@Composable private fun WelcomeScreen(onBook: () -> Unit) {
    Column(Modifier.fillMaxSize().padding(24.dp), verticalArrangement = Arrangement.SpaceBetween) {
        Column {
            Text("MIH", style = MaterialTheme.typography.displaySmall, color = MaterialTheme.colorScheme.primary, fontWeight = FontWeight.Black)
            Text("DELIVERY", style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.secondary, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(54.dp))
            Text("Delivery across NCR,\non your schedule.", style = MaterialTheme.typography.displaySmall, fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(12.dp))
            Text("Send anything from 1 gram to 20 tonnes with trusted delivery partners.", style = MaterialTheme.typography.bodyLarge)
            Spacer(Modifier.height(38.dp))
            Feature("📍", "Door-to-door", "Pick-up and drop-off anywhere in NCR")
            Feature("🛡", "Protected deliveries", "Optional insurance for eligible shipments")
            Feature("⚡", "Live updates", "Know where your delivery is at every step")
        }
        Button(onClick = onBook, Modifier.fillMaxWidth().height(56.dp), shape = RoundedCornerShape(16.dp)) { Text("Book a delivery") }
    }
}
@Composable private fun Feature(icon: String, title: String, subtitle: String) { Row(Modifier.padding(vertical = 11.dp), verticalAlignment = Alignment.CenterVertically) { Text(icon, style = MaterialTheme.typography.headlineSmall); Spacer(Modifier.width(16.dp)); Column { Text(title, fontWeight = FontWeight.Bold); Text(subtitle, style = MaterialTheme.typography.bodySmall) } } }

@OptIn(ExperimentalMaterial3Api::class)
@Composable private fun BookingScreen(onBack: () -> Unit, onConfirm: () -> Unit) {
    var source by rememberSaveable { mutableStateOf("") }; var destination by rememberSaveable { mutableStateOf("") }
    var item by rememberSaveable { mutableStateOf("") }; var weight by rememberSaveable { mutableStateOf("") }
    var insurance by rememberSaveable { mutableStateOf(false) }; var accepted by rememberSaveable { mutableStateOf(false) }
    val valid = source.isNotBlank() && destination.isNotBlank() && item.isNotBlank() && weight.isNotBlank() && accepted
    Scaffold(topBar = { TopAppBar(title = { Text("New delivery") }, navigationIcon = { TextButton(onClick = onBack) { Text("Back") } }) }, bottomBar = { Surface(shadowElevation = 8.dp) { Button(onClick = onConfirm, enabled = valid, modifier = Modifier.fillMaxWidth().padding(16.dp).height(54.dp), shape = RoundedCornerShape(14.dp)) { Text("Review delivery") } } }) { padding ->
        Column(Modifier.padding(padding).padding(horizontal = 20.dp).verticalScroll(rememberScrollState())) {
            Text("Where should we collect and deliver?", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 12.dp, bottom = 16.dp))
            Input("Pick-up address", source) { source = it }; Input("Drop-off address", destination) { destination = it }
            Text("Shipment", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 16.dp, bottom = 8.dp))
            Input("Item type (e.g. Documents, Furniture)", item) { item = it }; Input("Weight (up to 20 tonnes)", weight) { weight = it }
            Row(Modifier.fillMaxWidth().padding(vertical = 12.dp), verticalAlignment = Alignment.CenterVertically) { Column(Modifier.weight(1f)) { Text("Add shipment insurance", fontWeight = FontWeight.SemiBold); Text("Protection for eligible items", style = MaterialTheme.typography.bodySmall) }; Switch(checked = insurance, onCheckedChange = { insurance = it }) }
            Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF4DB)), modifier = Modifier.padding(top = 8.dp)) { Row(Modifier.padding(14.dp), verticalAlignment = Alignment.Top) { Checkbox(checked = accepted, onCheckedChange = { accepted = it }); Text("I confirm this shipment does not contain prohibited goods, is correctly labelled, and I accept the MIH delivery terms. Alcohol and cigarettes must comply with authorised limits.", style = MaterialTheme.typography.bodySmall, modifier = Modifier.padding(top = 12.dp)) } }
            Spacer(Modifier.height(90.dp))
        }
    }
}
@Composable private fun Input(label: String, value: String, onValue: (String) -> Unit) { OutlinedTextField(value = value, onValueChange = onValue, label = { Text(label) }, modifier = Modifier.fillMaxWidth().padding(vertical = 6.dp), singleLine = true) }

@Composable private fun ConfirmationScreen(onNewBooking: () -> Unit) { Column(Modifier.fillMaxSize().padding(24.dp), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) { Text("✓", style = MaterialTheme.typography.displayLarge, color = MaterialTheme.colorScheme.primary); Spacer(Modifier.height(16.dp)); Text("Delivery request created", style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold); Spacer(Modifier.height(8.dp)); Text("We’ll match your shipment with a delivery agent shortly. You will receive live updates.", style = MaterialTheme.typography.bodyLarge); Spacer(Modifier.height(28.dp)); AssistChip(onClick = {}, label = { Text("Tracking ID  MIH-48291") }); Spacer(Modifier.height(32.dp)); Button(onClick = onNewBooking, Modifier.fillMaxWidth()) { Text("Book another delivery") } } }
