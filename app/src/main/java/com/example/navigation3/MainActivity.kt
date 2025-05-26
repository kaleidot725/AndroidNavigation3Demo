package com.example.navigation3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.example.navigation3.ui.theme.Navigation3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { NavExample() }
    }
}

data object Home
data class Product(val id: String)

@Composable
fun NavExample() {
    val backStack = remember { mutableStateListOf<Any>(Home) }
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is Home -> NavEntry(key) {
                    HomeScreen(
                        onNavigateToProduct = { backStack.add(Product("123")) },
                        onBack = { backStack.removeLastOrNull() },
                        modifier = Modifier.fillMaxSize()
                    )
                }

                is Product -> NavEntry(key) { Text("Product") }
                else -> NavEntry(Unit) { Text("Unknown route") }
            }
        }
    )
}

@Composable
private fun HomeScreen(
    modifier: Modifier = Modifier,
    onNavigateToProduct: () -> Unit = {},
    onBack: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .navigationBarsPadding()
            .statusBarsPadding()
    ) {
        Text(text = "HOME")
        Button(onClick = onNavigateToProduct) { Text(text = "Navigate to product") }
        Button(onClick = onBack) { Text(text = "Back") }
    }
}